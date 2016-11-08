package com.personal.app;
 
import java.awt.HeadlessException;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem; 

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.personal.app.controlador.AsistenciaControlador;
import com.personal.app.controlador.Controlador;
import com.personal.app.controlador.PersonaControlador;

/**
 * App 
 *
 */
public class App extends JFrame
{
	private JMenuBar 	menuBar;
	
	private ApplicationContext context;
	
 
	private JMenu  		mnPersonal;
	private JMenuItem	miPersonal;
	private JMenuItem	miAsistencia;
	private Controlador controlador;

	public App() throws HeadlessException {
		super();
		context = new AnnotationConfigApplicationContext(AppConfiguracion.class);
		setTitle("Manejo de Personal ");
		setBounds(getMargenX(),getMargenY(),getWidthPantalla(),getHeightPantalla());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
	    setVisible(true);
	    
	    menuBar = new JMenuBar();
	    
	    mnPersonal = new JMenu("Personal");
	    miPersonal = new JMenuItem("Personal");
	    miPersonal.addActionListener(accionPersonal());
	    miAsistencia = new JMenuItem("Asistencia");
	    miAsistencia.addActionListener(accionAsistencia());
	    
	    menuBar.add(mnPersonal);
	    mnPersonal.add(miPersonal);
	    mnPersonal.add(miAsistencia);
	    setJMenuBar(menuBar);

	}

	public ActionListener accionPersonal()
	{
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controlador = new PersonaControlador(context);
				App.this.dibujar();
				
			}
		};
	}

	public ActionListener accionAsistencia()
	{
		return new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controlador = new AsistenciaControlador(context);
				App.this.dibujar();
			}
		};
	}
	
	protected void dibujar() {
		// TODO Auto-generated method stub
		getContentPane().removeAll();
		controlador.dibujar(this);
		revalidate();
		repaint();
	}
	
	public static Integer getWidthPantalla()
	{
		Rectangle screenSize =  GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		return (int) (screenSize.width*0.9);
	}
	
	public static Integer getHeightPantalla()
	{
		Rectangle screenSize =  GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		return (int) (screenSize.height*0.9);
	}

	public static Integer getMargenX()
	{
		Rectangle screenSize =  GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		return (int) (screenSize.width*0.08);
	}
	
	public static Integer getMargenY()
	{
		Rectangle screenSize =  GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		return (int) (screenSize.height*0.08);
	}

	public static void main( String[] args )
    {
    	new App();
    }
}
