import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeTarefas {
    public static void main(String[] args) {

        List<String> tarefasPendentes = new ArrayList<>();
        List<String> tarefasConcluidas = new ArrayList<>();

        String menu = """
                GERENCIADOR DE TAREFAS
                
                1 - Adicionar tarefas
                2 - Marcar tarefas como concluída
                3 - Listar todas as tarefas
                4 - Sair\n
                Escolha uma opção:
                """;

        while (true) {
            String opcao = (String) JOptionPane.showInputDialog(null, menu, "Menu", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icones/verificar.png"), null, null);

            if (opcao == null) break; // Caso o usuário clique em cancelar, encerra o programa

            switch (opcao) {
                case "1": // Cria nova tarefa
                    String novaTarefa = (String) JOptionPane.showInputDialog(null, "Digite a nova tarefa:", "Nova tarefa", JOptionPane.QUESTION_MESSAGE, new ImageIcon("icones/adicionar-ficheiro.png"), null, null);
                    if (novaTarefa != null && !novaTarefa.trim().isEmpty()) {
                        tarefasPendentes.add(novaTarefa);
                        JOptionPane.showMessageDialog(null, "Tarefa adicionada com sucesso!", "Nova tarefa", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icones/sucesso.png"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Tarefa inválida!");
                    }
                    break;
                case "2": // Verifica se existem tarefas pendentes e altera status de pendente para concluida
                    if (tarefasPendentes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Não há tarefas pendentes!", "Alerta", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icones/aviso.png"));
                    } else {
                        int indice = buscarTarefa(tarefasPendentes);
                        if (indice >= 0) {
                            tarefasConcluidas.add(tarefasPendentes.remove(indice));
                            JOptionPane.showMessageDialog(null, "Tarefa marcada como concluída!", "Marca tarefa", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icones/sucesso.png"));
                        }
                    }
                    break;
                case "3": // Opção 3 exibe tarefas pendnetes e as que foram concluídas
                    String listaTarefas = "LISTA DE TAREFAS\n" + listarTarefasPendentes(tarefasPendentes) + listarTarefasConcluidas(tarefasConcluidas);
                    JOptionPane.showMessageDialog(null, listaTarefas, "Tarefas", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icones/icone-principal.png"));
                    break;
                case "4": // Sai do programa
                    JOptionPane.showMessageDialog(null, "Saindo do programa...", "Sair", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Informe um valor válido. Tente novamente!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String listarTarefasPendentes(List<String> tarefasPendentes) {
        StringBuilder tarefas = new StringBuilder("\nTarefas Pendentes:\n");
        for (int i = 0; i < tarefasPendentes.size(); i++) {
            tarefas.append(i + 1).append(" - ").append(tarefasPendentes.get(i)).append("\n");
        }
        return tarefas.toString();
    }

    public static String listarTarefasConcluidas(List<String> tarefasConcluidas) {
        StringBuilder tarefas = new StringBuilder("\nTarefas Concluídas:\n");
        for (int i = 0; i < tarefasConcluidas.size(); i++) {
            tarefas.append("✅ ").append(tarefasConcluidas.get(i)).append("\n");
        }
        return tarefas.toString();
    }

    public static int buscarTarefa (List<String> listaPendetes){
        try {
            String escolha = JOptionPane.showInputDialog(listarTarefasPendentes(listaPendetes) + "\nDigite o número da terefa concluída:");
            if (escolha != null && !escolha.trim().isEmpty()){

                int indice = Integer.parseInt(escolha) - 1;
                if(indice >= 0 && indice < listaPendetes.size()) return indice;
                else JOptionPane.showMessageDialog(null, "Número inválido!", "Error", JOptionPane.ERROR_MESSAGE);

            } else JOptionPane.showMessageDialog(null, "Número inválido!", "Error", JOptionPane.ERROR_MESSAGE);

        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Entrada inválida. " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, new ImageIcon("icones/aviso.png"));
        }
        return -1;
    }
}
