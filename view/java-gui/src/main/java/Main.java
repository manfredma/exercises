
package start;

import java.awt.*;//必须引入的两个包

import javax.swing.*;//

public class Main extends JFrame {
    //定义控件
    JLabel labeln;//北部
    JButton btn1, btn2, btn3;
    JPanel panels; //一体的 //南部

    JTabbedPane panemid;//选项卡

    //中部
    JPanel panel1, panel2, panel3;
    JLabel label1, label2, label3, label4, label5, label6, label7, label8, label33, label44;
    JTextField text, text1, text2;


    JPasswordField password, password1, password2;


    JButton btnOfmodify, btnOfmodify2;


    JCheckBox checkbox1, checkbox2;


    JCheckBox checkbox3, checkbox4;


    public static void main(String[] args) {
        Main lx = new Main();
    }


    public Main() {


        //北部
        labeln = new JLabel(new ImageIcon("image/6.jpg"));//北部
        //中部
        label1 = new JLabel("QQ号码", JLabel.CENTER);
        label2 = new JLabel("QQ密码", JLabel.CENTER);
        label3 = new JLabel("忘记密码", JLabel.CENTER);
        label3.setFont(new Font("楷体", Font.PLAIN, 16));
        label3.setForeground(Color.black);//设置字体颜色
        label33 = new JLabel("忘记密码", JLabel.CENTER);
        label4 = new JLabel("<html><a href='www.qq.com'>申请密码保护</a>");
        label4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//设置手形，鼠标放上去显示(手...)
        label44 = new JLabel("<html><a href='www.qq.com'>申请密码保护</a>");
        label44.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label5 = new JLabel("管理员账户", JLabel.CENTER);
        label6 = new JLabel("管理员密码", JLabel.CENTER);
        label7 = new JLabel("您的靓号", JLabel.CENTER);
        label8 = new JLabel("密码", JLabel.CENTER);
        label7.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        label8.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        text = new JTextField();
        text1 = new JTextField();
        text2 = new JTextField();
        password = new JPasswordField();
        password1 = new JPasswordField();
        password2 = new JPasswordField();
        btnOfmodify = new JButton(new ImageIcon("image/1.png"));
        btnOfmodify2 = new JButton(new ImageIcon("image/1.png"));
        checkbox1 = new JCheckBox("隐身登录");
        checkbox2 = new JCheckBox("记住密码");
        checkbox3 = new JCheckBox("隐身登录");
        checkbox4 = new JCheckBox("记住密码");
        //南部
        btn1 = new JButton(new ImageIcon("image/2.png"));
        btn2 = new JButton(new ImageIcon("image/3.png"));
        btn3 = new JButton(new ImageIcon("image/4.png"));
        panels = new JPanel();
        //中部
        panemid = new JTabbedPane(); //选项卡
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panemid.add("普通用户", panel1);
        panemid.add("VIP用户", panel2);
        panemid.add("管理员", panel3);
        panel1.setLayout(new GridLayout(3, 3));
        panel2.setLayout(new GridLayout(3, 3));
        panel3.setLayout(new GridLayout(2, 2));
        panel1.add(label1);
        panel1.add(text);
        panel1.add(btnOfmodify);


        panel1.add(label2);
        panel1.add(password);
        panel1.add(label3);


        panel1.add(checkbox1);
        panel1.add(checkbox2);
        panel1.add(label4);


        panel2.add(label7);
        panel2.add(text1);
        panel2.add(btnOfmodify2);


        panel2.add(label8);
        panel2.add(password1);
        panel2.add(label33);


        panel2.add(checkbox3);
        panel2.add(checkbox4);
        panel2.add(label44);


        panel3.add(label5);
        panel3.add(text2);


        panel3.add(label6);
        panel3.add(password2);


        panels.add(btn1);
        panels.add(btn2);
        panels.add(btn3);//南部按钮的组合


        this.add(labeln, BorderLayout.NORTH);


        this.add(panels, BorderLayout.SOUTH);


        this.add(panemid, BorderLayout.CENTER); //默认是中间


        ImageIcon tp1 = new ImageIcon("image/23.png");


        this.setIconImage(tp1.getImage());


        this.setTitle("用户登录");


        this.setSize(800, 600);


        this.setLocation(300, 280);


        this.setResizable(false);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.setVisible(true);


    }


}
 
 

