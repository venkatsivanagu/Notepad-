import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.*;

class notepad {
static String text, family;
static int size=24,style=0;

    public static void main(String[] args) {
        JFrame f = new JFrame();
	f.setTitle("Untitled");
	ImageIcon mainIcon = new ImageIcon(ClassLoader.getSystemResource("main-icon.png"));
	Image notepadIcon = mainIcon.getImage();
	f.setIconImage(notepadIcon);
        f.setSize(950,800);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(new Color(82,82,82));

        FlowLayout flr = new FlowLayout(FlowLayout.RIGHT);

        Font fn = new Font("Comic Sans Ms",0,20);

        JTextArea ta = new JTextArea();
        f.add(ta);
	ta.setCaretColor(Color.red);
        ta.setBounds(0,0,700,800);   ta.setFont(fn); ta.setLineWrap(true);
        ta.setBackground(new Color(45,45,45)); ta.setForeground(Color.white);

        JMenuBar mb = new JMenuBar();
        f.setJMenuBar(mb);
        mb.setBackground(new Color(20,20,20));

	ImageIcon icon = new ImageIcon("setings.png");
	JButton ico = new JButton(icon);
	Border border = BorderFactory.createLineBorder(new Color(20,20,20));	
		
	ico.setBackground(new Color(20,20,20));		ico.setBounds(500,10,10,10);	ico.setBorder(border);

        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu view = new JMenu("View");

        mb.add(file);   mb.add(edit);   mb.add(view);	mb.add(ico);

        JMenuItem open = new JMenuItem("Open");	open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        JMenuItem save = new JMenuItem("Save");	save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
	JMenuItem New = new JMenuItem("New");	New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        JMenuItem copy = new JMenuItem("Copy");	copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        JMenuItem paste = new JMenuItem("Paste");	paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
	JMenuItem cut = new JMenuItem("Cut");	cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
	JMenuItem selectAll = new JMenuItem("Select All");
								selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
	JMenuItem exit = new JMenuItem("Exit");	exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
        JMenuItem zoom_in = new JMenuItem("Zoom_In");
								zoom_in.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ADD,ActionEvent.CTRL_MASK));
        JMenuItem zoom_out = new JMenuItem("Zoom_Out");
								zoom_out.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT,ActionEvent.CTRL_MASK));

        file.add(open);	  file.add(save);	file.add(New);	file.add(exit);		file.setForeground(Color.white);
        edit.add(copy);	  edit.add(paste);	edit.add(cut);	edit.add(selectAll);	edit.setForeground(Color.white);
        view.add(zoom_in);	view.add(zoom_out);  view.setForeground(Color.white);

        class ml implements ActionListener{
            public void actionPerformed(ActionEvent a){
		JFileChooser fc = new JFileChooser();
                if(a.getSource() == open){
                    int ret = fc.showDialog(null,"Open");
		    File file = fc.getSelectedFile();	
		   	if(ret == JFileChooser.APPROVE_OPTION){
				try{
					BufferedReader reader = new BufferedReader(new FileReader(file));
					ta.read(reader,null);
					}
				catch(Exception e){}
			}
                }
		else if(a.getSource() == save){
			JFileChooser saveAs = new JFileChooser("Save the File");
			int action = saveAs.showSaveDialog(f);
			File fileName = new File(saveAs.getSelectedFile() + ".txt");
			BufferedWriter outFile = null;
			f.setTitle(fileName.getName());
			try{
				outFile = new BufferedWriter(new FileWriter(fileName));;
				ta.write(outFile);
				}
			catch(Exception e){}
		}
		else if(a.getSource() == New){
			ta.setText("");
		}
		else if(a.getSource() == exit){
			System.exit(0);
		}
		else if(a.getSource() == copy){
			text = ta.getSelectedText();
		}
		else if(a.getSource() == paste){
			ta.insert(text,ta.getCaretPosition());
		}
		else if(a.getSource() == cut){
			ta.replaceRange("",ta.getSelectionStart(),ta.getSelectionEnd());
		}
		else if(a.getSource() == selectAll){
			ta.selectAll();
		} 
		else if(a.getSource() == zoom_in){
			int newsize = fn.getSize();
			newsize += 2;
			ta.setFont(fn.deriveFont(0,newsize));
		}
		else if(a.getSource() == zoom_out){
			int newsize = fn.getSize();
			newsize -= 2;
			ta.setFont(fn.deriveFont(0,newsize));
		}
	        else if(a.getSource() == ico){
		JFrame f2 = new JFrame("SETTINGS");
		ImageIcon mainIcon = new ImageIcon(ClassLoader.getSystemResource("main-icon.png"));
		Image notepadIcon = mainIcon.getImage();
		f2.setIconImage(notepadIcon);
		f2.setSize(700,800);
        	f2.setLocationRelativeTo(f);
        	f2.setDefaultCloseOperation(f.HIDE_ON_CLOSE);
        	f2.getContentPane().setBackground(new Color(45,45,45));
		f2.setLayout(null);	

		Font fm = new Font("Comic Sans Ms",1,34);
		Border border = BorderFactory.createLineBorder(new Color(20,20,20),5);
		
		JLabel font = new JLabel("Font");
		font.setForeground(Color.white);
		font.setBounds(50,115,200,54);	font.setFont(fm);
		f2.add(font);

		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		JComboBox<String> FontFamily = new JComboBox<>(fonts);
		f2.add(FontFamily);
		FontFamily.setBounds(50,180,300,30);	FontFamily.setBackground(Color.black);	FontFamily.setForeground(Color.white);
		FontFamily.setBorder(border);
		FontFamily.setOpaque(true);

		JLabel s = new JLabel("Size");
		s.setForeground(Color.white);
		s.setBounds(50,210,200,54);	s.setFont(fm);
		f2.add(s);

		Integer[] sizes = {8,10,12,14,16,18,20,22,24,26,28,30,32,36,38,40,42,44,46,48,50,52,54,56,58,60,62,64,66,68,70,72,74,76,78,80};
		JComboBox<Integer> allSizes = new JComboBox<>(sizes);
		f2.add(allSizes);
		allSizes.setBounds(50,280,300,30);	allSizes.setBackground(Color.black);	allSizes.setForeground(Color.white);
	
		JLabel st = new JLabel("Style");
		st.setForeground(Color.white);
		st.setBounds(50,310,200,54);	st.setFont(fm);
		f2.add(st);

		String[] styles = {"PLAIN","BOLD","ITALIC"};
		JComboBox<String> style = new JComboBox<>(styles);
		f2.add(style);
		style.setBounds(50,370,300,30);	style.setBackground(Color.black);	style.setForeground(Color.white);

		String[] app = {"App Theme","light","dark"};
		JComboBox<String> cb = new JComboBox<>(app);
		f2.add(cb);	

		cb.setBounds(50,80,300,30);	cb.setBackground(Color.black);	cb.setForeground(Color.white);	cb.setBorder(border);
		cb.setOpaque(true);

		JLabel la = new JLabel("Settings");
	
		la.setForeground(Color.white);
		la.setBounds(50,20,200,54);	la.setFont(fm);
		f2.add(la);

		class it implements ItemListener{
			public void itemStateChanged(ItemEvent e){
				if(cb.getSelectedItem() == "light"){
					ta.setBackground(Color.white);
					ta.setForeground(Color.black);
					mb.setBackground(new Color(245,245,245));
					file.setForeground(Color.black);
					edit.setForeground(Color.black);
					view.setForeground(Color.black);
					Border border = BorderFactory.createLineBorder(new Color(245,245,245));
					ico.setBorder(border);	ico.setBackground(new Color(245,245,245));
					cb.setBackground(Color.white);	cb.setForeground(Color.black);	cb.setBorder(border);
					FontFamily.setBackground(Color.white);	FontFamily.setForeground(Color.black);	FontFamily.setBorder(border);
					style.setBackground(Color.white);	style.setForeground(Color.black);	style.setBorder(border);
					allSizes.setBackground(Color.white);	allSizes.setForeground(Color.black);	allSizes.setBorder(border);
					la.setForeground(Color.black);
					font.setForeground(Color.black);
					s.setForeground(Color.black);
					st.setForeground(Color.black);
					f2.getContentPane().setBackground(Color.white);
				}
				else if(cb.getSelectedItem() == "dark"){
					ta.setBackground(new Color(45,45,45));
					ta.setForeground(Color.white);
					mb.setBackground(new Color(20,20,20));
					file.setForeground(Color.white);
					edit.setForeground(Color.white);
					view.setForeground(Color.white);
					Border border = BorderFactory.createLineBorder(new Color(20,20,20));
					ico.setBorder(border);	ico.setBackground(new Color(20,20,20));
					cb.setBackground(Color.black);	cb.setForeground(Color.white);	cb.setBorder(border);
					FontFamily.setBackground(Color.black);	FontFamily.setForeground(Color.white);	FontFamily.setBorder(border);
					style.setBackground(Color.black);	style.setForeground(Color.white);	style.setBorder(border);
					allSizes.setBackground(Color.black);	allSizes.setForeground(Color.white);	allSizes.setBorder(border);
					la.setForeground(Color.white);
					font.setForeground(Color.white);
					s.setForeground(Color.white);
					st.setForeground(Color.white);
					f2.getContentPane().setBackground(new Color(45,45,45));
				}
				else if(e.getSource() == FontFamily || e.getSource() == allSizes ||e.getSource() == style){
				ta.setFont(new Font((String)FontFamily.getSelectedItem(),(int)style.getSelectedIndex(),(int)allSizes.getSelectedItem()));
				}
			}
		}

		it item = new it();
		cb.addItemListener(item);
		FontFamily.addItemListener(item);
		allSizes.addItemListener(item);
		style.addItemListener(item);
		
		f2.setVisible(true);
	     }
            }
        }

        ml l = new ml();
      	open.addActionListener(l);	New.addActionListener(l);	exit.addActionListener(l);
	save.addActionListener(l);	copy.addActionListener(l);	paste.addActionListener(l);
	ico.addActionListener(l);		cut.addActionListener(l);		selectAll.addActionListener(l);
	zoom_in.addActionListener(l);	zoom_out.addActionListener(l);

        f.setVisible(true);
    }
}
