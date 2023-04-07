package kerekparBolt;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class App {

	private JFrame frameKerekparbolt;
	private JTable tableKerekparok;
	private JButton btnLegolcsobbHasznaltMtb;
	private JButton btnGyerekkerkparokSzama;
	private JButton btnTrekkingFerfi;
	private JLabel lblGyerekkerekparokSzama;
	private JButton btnFajlBeolvas;
	private JButton btnHibasAdatTorlese;
	private JButton btnKilep;
	private List<Kerekpar> kerekparok;//3.

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frameKerekparbolt.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unused")
	private void initialize() {
		frameKerekparbolt = new JFrame();
		frameKerekparbolt.setTitle("Kerekp\u00E1rbolt");
		frameKerekparbolt.setBounds(100, 100, 904, 668);
		frameKerekparbolt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameKerekparbolt.getContentPane().setLayout(null);
		
		JScrollPane scrollPaneKerekparok = new JScrollPane();
		scrollPaneKerekparok.setBounds(10, 11, 868, 234);
		frameKerekparbolt.getContentPane().add(scrollPaneKerekparok);
		
		tableKerekparok = new JTable();
		scrollPaneKerekparok.setViewportView(tableKerekparok);
		
		btnFajlBeolvas = new JButton("F\u00E1jlbeolvas\u00E1s");
		btnFajlBeolvas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FajlKezelo fkObj = new FajlKezelo();//1.
				kerekparok = fkObj.fajlBeolvas("kerekparok.csv");//2.
				
				DefaultTableModel tableKerekparokModel = new DefaultTableModel(new Object[] {//4.
						"Cikkszám",
						"Név",
						"Típus",
						"Ár (Ft)",
						"Használt",
						"Kategória",
						"Rögzítés dátuma"},0);
				
				for (Kerekpar kerekpar : kerekparok) {//5.
					tableKerekparokModel.addRow(new Object [] {
							kerekpar.getCikkSzam(),
							kerekpar.getNev(),
							kerekpar.getTipus(),
							kerekpar.getAr(),
							kerekpar.getHasznaltSzovegesen(),
							kerekpar.getRogzitesDatuma()
					});	
				}
				tableKerekparok.setModel(tableKerekparokModel); //6.
				
				//7. táblázat középreigazítás
				DefaultTableCellRenderer kozepreIgazitas = new DefaultTableCellRenderer();
				kozepreIgazitas.setHorizontalAlignment(JLabel.CENTER);
				for (int i = 0; i < tableKerekparokModel.getColumnCount(); i++) {
					tableKerekparok.getColumnModel().getColumn(i).setCellRenderer(kozepreIgazitas);	
				}
				//gombok engedélyezése
				btnFajlBeolvas.setEnabled(false);
				btnHibasAdatTorlese.setEnabled(true);
			}
		});
		btnFajlBeolvas.setBackground(Color.LIGHT_GRAY);
		btnFajlBeolvas.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFajlBeolvas.setBounds(645, 256, 222, 41);
		frameKerekparbolt.getContentPane().add(btnFajlBeolvas);
		
		btnLegolcsobbHasznaltMtb = new JButton("Legolcs\u00F3ss haszn\u00E1lt MTB");
		btnLegolcsobbHasznaltMtb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Kerekpar LegolcsobbHasznaltMtb = kerekparok.stream()
				.filter(c->c.getTipus().equalsIgnoreCase("mtb"))
				.filter(x->x.getHasznalt()==true)
				.min(Comparator.comparing(Kerekpar::getAr))
				.orElseThrow(NoSuchElementException::new); //null-ra szûrünk
				
//				System.out.println(LegolcsobbHasznaltMtb.getAr() + LegolcsobbHasznaltMtb.getNev());
				
				JOptionPane.showMessageDialog(frameKerekparbolt, "Legolcsóbb használt MTB neve és ára: "+LegolcsobbHasznaltMtb.getNev() + " / "+
				LegolcsobbHasznaltMtb.getAr() + " Ft",
				"Legolcsóbb használt MTB név / ár ",
				JOptionPane.PLAIN_MESSAGE
				);
			}
		});
		btnLegolcsobbHasznaltMtb.setEnabled(false);
		btnLegolcsobbHasznaltMtb.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLegolcsobbHasznaltMtb.setBackground(Color.LIGHT_GRAY);
		btnLegolcsobbHasznaltMtb.setBounds(25, 256, 250, 41);
		frameKerekparbolt.getContentPane().add(btnLegolcsobbHasznaltMtb);
		
		btnGyerekkerkparokSzama = new JButton("Gyerekker\u00E9kp\u00E1rok sz\u00E1ma");
		btnGyerekkerkparokSzama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Integer dbGyerekKerekparokSzam = 0;
//				for (Kerekpar kerekpar2 : kerekparok) {
//					if(kerekpar2.getKategoriaId() == 3) {
//						dbGyerekKerekparokSzam++;
//					}	
//				}
//				lblGyerekkerekparokSzama.setText(dbGyerekKerekparokSzam.toString());
//				lblGyerekkerekparokSzama.setVisible(true);
				
				//Stream() API-val ugyanez (ezt kell begyak)
				
				Long dbGyerekKerekparokSzam = kerekparok.stream().filter(i->i.getKategoriaId() == 3).count(); // azért Long, mert a count-nak Long a visszatérési értéke
				lblGyerekkerekparokSzama.setText(dbGyerekKerekparokSzam.toString());
				lblGyerekkerekparokSzama.setVisible(true);
				
			}
		});
		btnGyerekkerkparokSzama.setEnabled(false);
		btnGyerekkerkparokSzama.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGyerekkerkparokSzama.setBackground(Color.LIGHT_GRAY);
		btnGyerekkerkparokSzama.setBounds(25, 326, 250, 41);
		frameKerekparbolt.getContentPane().add(btnGyerekkerkparokSzama);
		
		btnTrekkingFerfi = new JButton("Trekking - F\u00E9rfi f\u00E1jlba\u00EDr");
		btnTrekkingFerfi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FajlKezelo fkObj = new FajlKezelo();
				fkObj.trekkingFajlbair(kerekparok);
				
				JOptionPane.showMessageDialog(frameKerekparbolt, "Sikeres ûvelet: létrejött a trekking_ferfi.csv",
						"Fájl létrehozva!!!",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnTrekkingFerfi.setEnabled(false);
		btnTrekkingFerfi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTrekkingFerfi.setBackground(Color.LIGHT_GRAY);
		btnTrekkingFerfi.setBounds(25, 399, 250, 41);
		frameKerekparbolt.getContentPane().add(btnTrekkingFerfi);
		
		btnKilep = new JButton("KIL\u00C9P");
		btnKilep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnKilep.setForeground(Color.WHITE);
		btnKilep.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnKilep.setBackground(Color.RED);
		btnKilep.setBounds(724, 450, 143, 61);
		frameKerekparbolt.getContentPane().add(btnKilep);
		
		btnHibasAdatTorlese = new JButton("Hib\u00E1s adat t\u00F6rl\u00E9se");
		btnHibasAdatTorlese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int hibasIndex = -1;
				for (int i = 0; i < kerekparok.size(); i++) {
					if(kerekparok.get(i).getCikkSzam().toString().length() !=6) {
						hibasIndex = i;
					}	
				}
				//adatszerkezetbõl index alapján töröljük - ArrayList
				kerekparok.remove(hibasIndex);
				//Felületrõl is töröljük a model segítségével
				((DefaultTableModel)tableKerekparok.getModel()).removeRow(hibasIndex); // azért kell castolni, mert csak defaulttablemodel-nél van remove row metódus
				
				//Gombok engedélyezése / tiltása
				btnHibasAdatTorlese.setEnabled(false);
				btnLegolcsobbHasznaltMtb.setEnabled(true);
				btnGyerekkerkparokSzama.setEnabled(true);
				btnTrekkingFerfi.setEnabled(true);
				
				}
		});
		btnHibasAdatTorlese.setEnabled(false);
		btnHibasAdatTorlese.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHibasAdatTorlese.setBackground(Color.LIGHT_GRAY);
		btnHibasAdatTorlese.setBounds(645, 326, 222, 41);
		frameKerekparbolt.getContentPane().add(btnHibasAdatTorlese);
		
		lblGyerekkerekparokSzama = new JLabel("");
		lblGyerekkerekparokSzama.setOpaque(true);
		lblGyerekkerekparokSzama.setBackground(Color.GREEN);
		lblGyerekkerekparokSzama.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGyerekkerekparokSzama.setHorizontalAlignment(SwingConstants.CENTER);
		lblGyerekkerekparokSzama.setBounds(333, 326, 280, 41);
		frameKerekparbolt.getContentPane().add(lblGyerekkerekparokSzama);
//		System.out.println(fkObj.fajlBeolvas("kerekparok.csv").get(0).getCikkSzam());  //debugolás, ha az elsõ sor cikkszáma szerepel akkor jó volt a beolvasás
	}
}
