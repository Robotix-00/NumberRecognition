package facharbeit.GUI.panels;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import facharbeit.GUI.KnnGUI;
import facharbeit.neuralNetwork.NeuralNetwork;

/*
 * Zur Einstellung von allen Variablen (KNN-Pfad)
 */
public class SettingPanel extends JPanel {
    private File networkFile;
    private Label networkLabel;

    public SettingPanel() {
	setLayout(null);

	// Netzwerk
	{
	    // Label
	    {
		networkFile = new File("KNNs/NeuralNetwork.knn");

		Label label;
		if (networkFile.exists())
		    label = new Label("Netzwerk: " + networkFile.getName());
		else
		    label = new Label("Netzwerk: ");
		label.setBounds(150, 20, 500, 20);

		networkLabel = label;
		this.add(networkLabel);
	    }
	    // File-Select
	    {
		JButton btn = new JButton("KNN auswählen");
		btn.setBounds(10, 15, 130, 25);
		this.add(btn);
		btn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("KNN", "knn"));
			chooser.setCurrentDirectory(new File("."));
			
			chooser.showOpenDialog(null);
			networkFile = chooser.getSelectedFile();
			
			if (networkFile == null || !networkFile.exists())
			    return;
			
			NeuralNetwork net = NeuralNetwork.load(networkFile);
			if (net != null) {
			    networkLabel.setText("Netzwerk: " + networkFile.getName());
			    KnnGUI.setNeuralNetwork(net);
			}
		    }
		});
	    }
	}
    }
}
