package kurorekishimain;

import static recommendation.Recommendation.Entity.COL_CATEGORY;
import static recommendation.Recommendation.Entity.COL_ENJOY;
import static recommendation.Recommendation.Entity.COL_LOC;
import static recommendation.Recommendation.Entity.COL_NAME;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import recommendation.Recommendation;
import recommendation.RecommendationDaoImpl;

public class DateFrame extends JFrame {

    // 데이트 추천창에서 보여줄 JTable의 컬럼들
    private static final String[] COLUMN_NAMES = {
            COL_NAME,COL_LOC,COL_CATEGORY,COL_ENJOY
    };
    private static final String[] categories= {"맛집", "액티비티", "정적인","산책"};
    public static final String[] locations = {"서울","경기","충북","충남","전북","전남","경북","경남","강원도","제주"};
    
    private JPanel contentPane;
    private Component parent;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnViewAll;
    private JButton btnSearch;
    private JComboBox comboBoxCategory;
    private JComboBox comboBoxLoc;
    private RecommendationDaoImpl dao;
    /**
     * Launch the application.
     * @param frame 
     */
    public static void newDateFrame(Component parent) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                    DateFrame frame = new DateFrame(parent);
                    frame.setVisible(true);
            }
        });
    }
    
    public DateFrame(Component parent) {
        this.parent = parent;
        dao = RecommendationDaoImpl.getInstance();
        initialize();
    }

    /**
     * Create the frame.
     */
    public void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        int x = parent.getX();
        int y = parent.getY();
        setBounds(x+665, y, 664, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setResizable(false);
        setTitle("데이트 추천");
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        comboBoxLoc = new JComboBox(locations);
        panel.add(comboBoxLoc);
        
        comboBoxCategory = new JComboBox(categories);
        panel.add(comboBoxCategory);
        
        btnSearch = new JButton("검색");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        panel.add(btnSearch);
        
        btnViewAll = new JButton("자세히 보기");
        btnViewAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                datail();
            }
        });
        panel.add(btnViewAll);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        model = new DefaultTableModel(null, COLUMN_NAMES);
        table.setModel(model);
        scrollPane.setViewportView(table);
        
    }// END initialize()
    
    private void datail() {
        List<Recommendation> list = new ArrayList<>();
        String loc = (String) comboBoxLoc.getSelectedItem();
        String category = (String) comboBoxCategory.getSelectedItem();
        list = dao.search(loc, category);
        int row = table.getSelectedRow();
        if(row == -1) {
            JOptionPane.showMessageDialog(this, "확인할 행을 먼저 선택하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Recommendation rec = list.get(row);
        ViewAll.newViewAll(parent,rec);
    }

    private void search() {
        // 값들을 가져온다
        List<Recommendation> list = new ArrayList<>();
        String loc = (String) comboBoxLoc.getSelectedItem();
        String category = (String) comboBoxCategory.getSelectedItem();
        
        // 이미 검색된 내용이 있으면 테이블을 한번 초기화 시킨다.
        resetTable();
        
        // 검색내용을 테이블에 추가한다.
        list = dao.search(loc,category);
        for(Recommendation r : list) {
            Object[] row = {r.getName(),r.getLoc(),r.getCategory(),r.getEnjoy()};
            model.addRow(row);
        }
        
    }

    private void resetTable() {
        model = new DefaultTableModel(null, COLUMN_NAMES);
        table.setModel(model);
    }
}
