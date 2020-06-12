package src;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import constantes.TipoDeArray;
import constantes.TipoDeOrdenamiento;

public class VentanaPrincipal extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private final int OFFSET_SEGUNDA_COL = 915;
	
	private PanelAnimado panelAnimado;
	private String ordenamientoSeleccionado;
	private String arraySeleccionado;
	
	public VentanaPrincipal() {
//		setPreferredSize(new Dimension(WIDTH, HEIGHT));
//		setLayout(new BorderLayout());
//		setTitle("Algoritmos de ordenamiento");
//		panelAnimado = new PanelAnimado();
//		add(panelAnimado, BorderLayout.NORTH);
//		
//		JLabel l = new JLabel("HOLA");
//		add(l, BorderLayout.SOUTH);
//		
//		
//		pack();
//		setLocationRelativeTo(null);
//		setVisible(true);
//		
//		//
		
		setTitle("Ordenamiento gráfico");
		setBounds(100, 100, 1050, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		panelAnimado = new PanelAnimado(); 
		panelAnimado.setBounds(10, 35, 900, 420);
		panelAnimado.setVisible(true);
		add(panelAnimado);


//		JScrollPane listaOrdenamientoscrollPane = new JScrollPane();
//		listaOrdenamientoscrollPane.setBounds(OFFSET_SEGUNDA_COL, 210, 128, 128);
//		getContentPane().add(listaOrdenamientoscrollPane);
//
//		JList ordenamientosJList = new JList( TipoDeOrdenamiento.values() );
//		listaOrdenamientoscrollPane.setViewportView(ordenamientosJList);
//		ordenamientosJList.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
//
//            @Override
//            public void valueChanged(ListSelectionEvent arg0) {
//                if (!arg0.getValueIsAdjusting()) {
//                	ordenamientoSeleccionado = ordenamientosJList.getSelectedValue().toString();
//                	panelAnimado.setOrdenamiento(ordenamientoSeleccionado);
//                }
//            }
//        });
//
//		JScrollPane listaArrayscrollPane = new JScrollPane();
//		listaArrayscrollPane.setBounds(OFFSET_SEGUNDA_COL, 35, 128, 128);
//		getContentPane().add(listaArrayscrollPane);
//		
//		JList tiposDeArrayJList = new JList( TipoDeArray.values() );
//		listaArrayscrollPane.setViewportView(tiposDeArrayJList);
//		tiposDeArrayJList.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
//
//            @Override
//            public void valueChanged(ListSelectionEvent arg0) {
//                if (!arg0.getValueIsAdjusting()) {
//                	arraySeleccionado = tiposDeArrayJList.getSelectedValue().toString();
//                }
//            }
//        });
//
//		JLabel arrayLabel = new JLabel("Tipo de Array:");
//		arrayLabel.setBounds(OFFSET_SEGUNDA_COL, 16, 93, 16);
//		getContentPane().add(arrayLabel);
//
//		JLabel ordernarLabel = new JLabel("Ordenamiento:");
//		ordernarLabel.setBounds(OFFSET_SEGUNDA_COL, 185, 128, 16);
//		getContentPane().add(ordernarLabel);
//
//		JButton StartBtn = new JButton("Start");
//		StartBtn.setBounds(OFFSET_SEGUNDA_COL, 343, 128, 29);
//		getContentPane().add(StartBtn);
//		StartBtn.addActionListener(new ActionListener(){  
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println(ordenamientoSeleccionado);
//				System.out.println(arraySeleccionado);
//				panelAnimado.ejecutarAlgoritmo( ordenamientoSeleccionado, arraySeleccionado, 200, 4);
//			}  
//		});
//
//		JLabel progresoLabel = new JLabel("Progreso:");
//		progresoLabel.setBounds(10, 16, 93, 16);
//		getContentPane().add(progresoLabel);

		setVisible(true);
		
	}
}
