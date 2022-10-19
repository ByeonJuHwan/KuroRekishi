package kurorekishimain;

import java.awt.EventQueue;
import static member.Member.Entity.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import chat.ChatDaoImpl;
import member.Member;
import member.MemberDaoImpl;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatRoomFrame extends JFrame {

    private static final String[] COLUMN_NAMES = {
            COL_MEM_NAME,COL_MEM_LOC,COL_MEM_AGE,COL_MEM_HISTORY
    };
    
    private static List<String> gavedIdList;
    private static List<Member> list = new ArrayList<>();
    
    private MemberDaoImpl dao;
    private ChatDaoImpl chatDao;
    
    
    private Component parent;
    private JPanel contentPane;
    public JTable table;
    public DefaultTableModel model;
    private String userId;
    

    /**
     * Launch the application.
     * @param name 
     */
    public static void newChatRoomFrame(Component parent,String userId) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                    ChatRoomFrame frame = new ChatRoomFrame(parent,userId);
                    frame.setVisible(true);
            }
        });
    }
    
    public ChatRoomFrame(Component parent,String userId) {   
        this.parent = parent;
        this.userId = userId;
        chatDao = ChatDaoImpl.getInstance();
        dao= MemberDaoImpl.getInstance();
        initialize();
        initializeTable(); // 화면에서의 JTable 데이터 초기화
    }


    private void initializeTable() {
        gavedIdList = chatDao.idList(userId);
        
        list = new ArrayList<>();
        for(String s : gavedIdList) {
            Member member = dao.findMemberById(s);
            list.add(member);
        }
        
        model = new DefaultTableModel(null, COLUMN_NAMES);
        for(Member m : list) {
            Object[] row = {m.getName(),m.getLocation(),m.getAge(),m.getHistory()};
            model.addRow(row);
        }
        table.setModel(model);
    }

    /**
     * Create the frame.
     */
    public void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        int x = parent.getX();
        int y = parent.getY();
        setBounds(x, y, 457, 536);
        setTitle("채팅목록");
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JButton btnNewButton = new JButton("채팅하기");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goChatTable();
            }
        });
        btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        contentPane.add(btnNewButton, BorderLayout.SOUTH);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        model = new DefaultTableModel(null, COLUMN_NAMES);
        table.setModel(model);
        scrollPane.setViewportView(table);
    }

    private void goChatTable() {
        int row = table.getSelectedRow();
        if(row == -1) {
            JOptionPane.showMessageDialog(parent, "채팅하고 싶은 사람을 선택해주세요.","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }else {
            giveThumb();
            JOptionPane.showMessageDialog(parent, "상대를 유혹하는 한마디를 던져보세요!");
            ChatFrame.newChatFrame(parent);
        }
        
    }

    private void giveThumb() {
        int row = table.getSelectedRow();
        Member member = list.get(row);
        String name = dao.fineName(userId);
        int result = dao.giveThumb(name,member.getName());
        if(result == 1) {
            JOptionPane.showMessageDialog(parent, member.getName() + " 님을 재촉합니다.");
            String id = dao.findIdByName(member.getName());
            chatDao.insertId(userId, id);
        }else {
            JOptionPane.showMessageDialog(parent, "좋아요를 보낼수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

}
