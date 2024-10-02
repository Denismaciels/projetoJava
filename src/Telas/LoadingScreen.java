/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Telas;
import Login.Usuario;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Denis
 */
public class LoadingScreen {
     private JFrame frame;
    private JLabel loadingLabel;
    private JProgressBar progressBar;

    public LoadingScreen() {
        // Configuração da janela de carregamento
        frame = new JFrame("Carregando...");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);

        // Configuração do layout
        frame.setLayout(new BorderLayout());

        // Configuração do texto de carregamento
        loadingLabel = new JLabel("Carregando: 0%", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(loadingLabel, BorderLayout.NORTH);

        // Configuração da barra de progresso
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        frame.add(progressBar, BorderLayout.CENTER);

        // Exibição da janela de carregamento
        frame.setVisible(true);
    }

    public void updateProgress(int progress) {
        loadingLabel.setText("Carregando: " + progress + "%");
        progressBar.setValue(progress);
    }

    public void close() {
        frame.dispose();
    }

    public static void main(String[] args) {
        // Criação da tela de carregamento
        LoadingScreen loadingScreen = new LoadingScreen();

        // SwingWorker para simular uma tarefa em segundo plano com atualização da barra de progresso
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    // Simulando uma tarefa longa
                    Thread.sleep(50); // Pausa de 50 ms
                    publish(i); // Publica o progresso
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                // Atualiza o progresso na tela
                int progress = chunks.get(chunks.size() - 1);
                loadingScreen.updateProgress(progress);
            }

            @Override
            protected void done() {
                // Fechar a tela de carregamento quando a tarefa for concluída
                loadingScreen.close();
                // Aqui você pode iniciar a próxima janela ou funcionalidade
                Login lo = new Login();
                lo.setVisible(true);
            }
        };

        // Iniciar a tarefa em segundo plano
        worker.execute();
    }
}
