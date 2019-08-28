package notepad;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import javax.swing.*;

public class NotePad extends JFrame 
{

    
    JMenuBar menubar;
    JMenu m1,m2,m3;
    JMenuItem i1,i2,i3,i4,i5,i6,i7,i8,i9,i10;
    JTextArea jta;
    JToolBar jtb;
    JButton b1,b2;
    public NotePad()
    {
        b1 = new JButton("Open");
        b1.setToolTipText("You can open File");
        b2 = new JButton("Save");
        b2.setToolTipText("You can save file");
        jtb = new JToolBar();
        jtb.add(b1);
        jtb.add(b2);
        jta = new JTextArea();
        JScrollPane jsp = new JScrollPane(jta);
        i1 = new JMenuItem("New");
        i2 = new JMenuItem("Open");
        i3 = new JMenuItem("Save");
        i4 = new JMenuItem("Exit");
        m1 = new JMenu("File");
        m1.setToolTipText("File Menu");
        m1.add(i1);m1.add(i2);m1.add(i3);m1.add(i4);
        i5 = new JMenuItem("Cut");
        i6 = new JMenuItem("Copy");
        i7 = new JMenuItem("Paste");
        m2 = new JMenu("Edit");
        m2.add(i5);m2.add(i6);m2.add(i7);
        i8 = new JMenuItem("Bold");
        i9 = new JMenuItem("Italic");
        i10 = new JMenuItem("Underline");
        m3 = new JMenu("Format");
        m3.add(i8);m3.add(i9);m3.add(i10);
        menubar = new JMenuBar();
        menubar.add(m1);menubar.add(m2);menubar.add(m3);
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2,1));
        p1.add(menubar);
        p1.add(jtb);
        c.add(p1,BorderLayout.NORTH);
        c.add(jsp,BorderLayout.CENTER);
        setVisible(true);
        setSize(600,600);
        i1.addActionListener(new al(this));
        i2.addActionListener(new al(this));
        i3.addActionListener(new al(this));
        i4.addActionListener(new al(this));
        i5.addActionListener(new al(this));
        i6.addActionListener(new al(this));
        i7.addActionListener(new al(this));
        i8.addActionListener(new al(this));
        i9.addActionListener(new al(this));
        i10.addActionListener(new al(this));
        b1.addActionListener(new al(this));
        b2.addActionListener(new al(this));
    }
    class al implements ActionListener
    {
      JFrame parent;
        private Object jtargetFont;
      public al(JFrame f)
      {
          parent=f;
      }
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getSource().equals(i1))
            {
                jta.replaceRange(" ",0, jta.getText().length());
            }
            if(e.getSource().equals(i2)||e.getSource().equals(b1))
            {
                jta.replaceRange(" ",0,jta.getText().length());
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(parent);
                if(returnVal==JFileChooser.APPROVE_OPTION)
                {
                    try
                    {
                        FileReader fr = new FileReader(chooser.getSelectedFile().getPath());
                        //BufferedWriter bw = new BufferedWriter(fr);
                        String data;
                        BufferedReader br = new BufferedReader(fr);
                        while((data=br.readLine())!=null)
                            jta.append(data+"\n");
                        br.close();
                        fr.close();
                    }
                    catch(Exception ex)
                    {
                        System.out.print(ex);
                    }
                }
            }
            if(e.getSource().equals(i3)||e.getSource().equals(b2))
            {
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showSaveDialog(parent);
                if(returnVal==JFileChooser.APPROVE_OPTION)
                {
                    try 
                    {
                        FileWriter fw = new FileWriter(chooser.getSelectedFile().getPath());
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(jta.getText());
                        bw.close();
                        fw.close();
                    }
                    catch(Exception ex)
                    {
                        System.out.print(ex);
                    }
                }
            }
            if(e.getSource().equals(i4))
                System.exit(0);
            if(e.getSource().equals(i5))
                jta.cut();
            if(e.getSource().equals(i6))
                jta.copy();
            if(e.getSource().equals(i7))
                jta.paste();
            if(e.getSource().equals(i8))
                jta.setFont(new Font("Century Gothic",Font.BOLD,jta.getFont().getSize()));
            if(e.getSource().equals(i9))
                jta.setFont(new Font("Century Gothic",Font.ITALIC,jta.getFont().getSize()));
            if(e.getSource().equals(i10))
                jta.setFont(new Font("Century Gothic",Font.CENTER_BASELINE,jta.getFont().getSize()));
        }
    }
    public static void main(String[] args) {
        new NotePad();
    }
    
}
