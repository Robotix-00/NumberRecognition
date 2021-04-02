package facharbeit.GUI;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import facharbeit.GUI.panels.*;
import facharbeit.neuralNetwork.NeuralNetwork;

/*
 * HauptFenster des GUIs - Verwaltung des KNNs und der Unterfenster
 */
public class KnnGUI extends JFrame {
    private static NeuralNetwork neuralNetwork;
    
    private JPanel contentPanel;
    private JTabbedPane tabbedPane;

    public KnnGUI() {
	// Fenstereinstellungen
	this.setBounds(100, 100, 633, 462);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.getContentPane().setLayout(null);
	this.setResizable(false);
	this.setTitle("Nummernerkennung");
	
	// 
	contentPanel = new JPanel();
	contentPanel.setBounds(0, 0, 617, 391);
	contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPanel.setLayout(null);
	
	getContentPane().add(contentPanel);

	// MenüAuswahl-Einstellungen
	tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	tabbedPane.setBounds(10, 10, 597, 371);

	// Hinzufügen der einzelnen Fenster
	{
	    tabbedPane.addTab("Erkennung", new RecognitionPanel());
	    tabbedPane.addTab("Tests", new TestPanel());
	    //tabbedPane.addTab("Display", new DisplayPanel());
	    //tabbedPane.addTab("Schreiben", new WritePanel());
	    tabbedPane.addTab("Einstellungen", new SettingPanel());
	}
	contentPanel.add(tabbedPane);
	
	
	//Laden des Standart-Netzes
	{
	    File defaultNetworkFile = new File("KNNs/NeuralNetwork.knn");
	    if (defaultNetworkFile.exists())
		neuralNetwork = NeuralNetwork.load(defaultNetworkFile);
	    else
		JOptionPane.showMessageDialog(this,
			"Das Standard-Netz konnte nicht geladen werden, bitte wähle ein anders aus", null, 2);
	}
    }

    public void start() {
	this.setVisible(true);
    }

    /*
     * Getter und Setter
     */
    public static NeuralNetwork getNeuralNetwork() {
	return neuralNetwork;
    }

    public static void setNeuralNetwork(NeuralNetwork neuralNetwork) {
	if (neuralNetwork != null)
	    KnnGUI.neuralNetwork = neuralNetwork;
    }

    public static boolean hasNeuralNetwork() {
	return neuralNetwork != null;
    }
}
