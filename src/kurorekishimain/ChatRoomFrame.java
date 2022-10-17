package kurorekishimain;

import java.awt.EventQueue;
import static member.Member.Entity.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import member.Member;
import member.MemberDaoImpl;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.util.List;

public class ChatRoomFrame extends JFrame {

    private static final String[] COLUMN_NAMES = {
            COL_MEM_NAME,COL_MEM_LOC,COL_MEM_AGE,COL_MEM_HISTORY
    };
    
    private MemberDaoImpl dao;
    
    private Component parent;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private String id;

    /**
     * Launch the application.
     */
    public static void newChatRoomFrame(Component parent,String id) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                    ChatRoomFrame frame = new ChatRoomFrame(parent,id);
                    frame.setVisible(true);
            }
        });
    }
    
    public ChatRoomFrame(Component parent,String id) {
        this.parent = parent;
        this.id = id;
        dao= MemberDaoImpl.getInstance();
        initialize();
        initializeTable(); // 화면에서의 JTable 데이터 초기화
    }

    private void initializeTable() {
        // TODO 상대방 정보가 나오게 수정 지금 자기자신 정보가나옴.
        List<Member> list = dao.selectById(id);
        for(Member m : list) {
            Object[] row = {m.getName(),m.getLocation(),m.getAge(),m.getHistory()};
            model.addRow(row);
        }
    }

    /**
     * Create the frame.
     */
    public void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 457, 536);
        setTitle("채팅목록");
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JButton btnNewButton = new JButton("채팅하기");
        btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        contentPane.add(btnNewButton, BorderLayout.SOUTH);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        model = new DefaultTableModel(null, COLUMN_NAMES);
        table.setModel(model);
        scrollPane.setViewportView(table);
    }

}
