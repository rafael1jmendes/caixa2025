package com.mendes.caixa2025.service;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import static org.hibernate.cfg.JdbcSettings.URL;
import static org.hibernate.cfg.JdbcSettings.USER;

public class SistemaMovimentacoes {

    // Configurações de conexão
    private static final String URL = "jdbc:postgresql://localhost:5432/meu_banco";
    private static final String USER = "caixa_adm";
    private static final String PASSWORD = "162619";
    private static final String SCHEMA = "caixa2025";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== SISTEMA DE MOVIMENTAÇÕES ===");
                System.out.println("1. Listar movimentações");
                System.out.println("2. Adicionar movimentação");
                System.out.println("3. Atualizar movimentação");
                System.out.println("4. Remover movimentação");
                System.out.println("5. Relatórios e filtros");
                System.out.println("6. Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer

                switch (opcao) {
                    case 1 -> listarMovimentacoes();
                    case 2 -> adicionarMovimentacao(scanner);
                    case 3 -> atualizarMovimentacao(scanner);
                    case 4 -> removerMovimentacao(scanner);
                    case 5 -> exibirRelatorios(scanner);
                    case 6 -> {
                        System.out.println("Saindo do sistema...");
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }
            }
        }
    }

    // Operação CRUD: Create
    public static void adicionarMovimentacao(Scanner scanner) {
        System.out.println("\n--- NOVA MOVIMENTAÇÃO ---");

        System.out.print("Tipo (1=Entrada, 2=Saída): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        String sql = "INSERT INTO " + SCHEMA + ".movimentacoes " +
                "(tipo_movimentacao, descricao, valor) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false); // Inicia transação

            pstmt.setInt(1, tipo);
            pstmt.setString(2, descricao);
            pstmt.setDouble(3, valor);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        System.out.println("Movimentação adicionada com ID: " + rs.getInt(1));
                        conn.commit(); // Confirma transação
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar movimentação: " + e.getMessage());
        }
    }

    // Operação CRUD: Read (com filtros)
    public static void listarMovimentacoes() {
        String sql = "SELECT id, tipo_movimentacao, descricao, valor, data_movimentacao " +
                "FROM " + SCHEMA + ".movimentacoes ORDER BY data_movimentacao DESC";

        exibirMovimentacoes(sql);
    }

    private static void exibirMovimentacoes(String sql, Object... params) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Setar parâmetros se existirem
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("\n--- LISTA DE MOVIMENTAÇÕES ---");
                System.out.printf("%-5s %-8s %-30s %-10s %-20s%n",
                        "ID", "Tipo", "Descrição", "Valor", "Data");
                System.out.println("------------------------------------------------------------");

                while (rs.next()) {
                    System.out.printf("%-5d %-8s %-30s R$ %-8.2f %-20s%n",
                            rs.getInt("id"),
                            rs.getInt("tipo_movimentacao") == 1 ? "Entrada" : "Saída",
                            rs.getString("descricao"),
                            rs.getDouble("valor"),
                            rs.getTimestamp("data_movimentacao").toLocalDateTime()
                                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar movimentações: " + e.getMessage());
        }
    }

    // Operação CRUD: Update
    public static void atualizarMovimentacao(Scanner scanner) {
        System.out.println("\n--- ATUALIZAR MOVIMENTAÇÃO ---");
        System.out.print("ID da movimentação a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Novo tipo (1=Entrada, 2=Saída): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nova descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Novo valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        String sql = "UPDATE " + SCHEMA + ".movimentacoes " +
                "SET tipo_movimentacao = ?, descricao = ?, valor = ? " +
                "WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            pstmt.setInt(1, tipo);
            pstmt.setString(2, descricao);
            pstmt.setDouble(3, valor);
            pstmt.setInt(4, id);

            int affectedRows = pstmt.executeUpdate();
            conn.commit();

            if (affectedRows > 0) {
                System.out.println("Movimentação atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma movimentação encontrada com o ID informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar movimentação: " + e.getMessage());
        }
    }

    // Operação CRUD: Delete
    public static void removerMovimentacao(Scanner scanner) {
        System.out.println("\n--- REMOVER MOVIMENTAÇÃO ---");
        System.out.print("ID da movimentação a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        String sql = "DELETE FROM " + SCHEMA + ".movimentacoes WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            conn.commit();

            if (affectedRows > 0) {
                System.out.println("Movimentação removida com sucesso!");
            } else {
                System.out.println("Nenhuma movimentação encontrada com o ID informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover movimentação: " + e.getMessage());
        }
    }

    // Relatórios e filtros
    public static void exibirRelatorios(Scanner scanner) {
        System.out.println("\n--- RELATÓRIOS E FILTROS ---");
        System.out.println("1. Resumo financeiro");
        System.out.println("2. Filtrar por tipo (Entrada/Saída)");
        System.out.println("3. Filtrar por período");
        System.out.println("4. Filtrar por valor mínimo");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> exibirResumoFinanceiro();
            case 2 -> filtrarPorTipo(scanner);
            case 3 -> filtrarPorPeriodo(scanner);
            case 4 -> filtrarPorValorMinimo(scanner);
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void exibirResumoFinanceiro() {
        String sqlEntradas = "SELECT COALESCE(SUM(valor), 0) FROM " + SCHEMA + ".movimentacoes " +
                "WHERE tipo_movimentacao = 1";
        String sqlSaidas = "SELECT COALESCE(SUM(valor), 0) FROM " + SCHEMA + ".movimentacoes " +
                "WHERE tipo_movimentacao = 2";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rsEntradas = stmt.executeQuery(sqlEntradas);
             ResultSet rsSaidas = stmt.executeQuery(sqlSaidas)) {

            rsEntradas.next();
            rsSaidas.next();

            double totalEntradas = rsEntradas.getDouble(1);
            double totalSaidas = rsSaidas.getDouble(1);
            double saldo = totalEntradas - totalSaidas;

            System.out.println("\n--- RESUMO FINANCEIRO ---");
            System.out.printf("Total de Entradas: R$ %.2f%n", totalEntradas);
            System.out.printf("Total de Saídas: R$ %.2f%n", totalSaidas);
            System.out.printf("Saldo Atual: R$ %.2f%n", saldo);

        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    private static void filtrarPorTipo(Scanner scanner) {
        System.out.print("Tipo (1=Entrada, 2=Saída): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        String sql = "SELECT id, tipo_movimentacao, descricao, valor, data_movimentacao " +
                "FROM " + SCHEMA + ".movimentacoes WHERE tipo_movimentacao = ? " +
                "ORDER BY data_movimentacao DESC";

        exibirMovimentacoes(sql, tipo);
    }

    private static void filtrarPorPeriodo(Scanner scanner) {
        System.out.println("Informe o período (formato DD/MM/AAAA)");
        System.out.print("Data inicial: ");
        String dataInicial = scanner.nextLine();

        System.out.print("Data final: ");
        String dataFinal = scanner.nextLine();

        String sql = "SELECT id, tipo_movimentacao, descricao, valor, data_movimentacao " +
                "FROM " + SCHEMA + ".movimentacoes " +
                "WHERE data_movimentacao BETWEEN ?::timestamp AND ?::timestamp " +
                "ORDER BY data_movimentacao DESC";

        exibirMovimentacoes(sql, dataInicial + " 00:00:00", dataFinal + " 23:59:59");
    }

    private static void filtrarPorValorMinimo(Scanner scanner) {
        System.out.print("Valor mínimo: ");
        double valorMinimo = scanner.nextDouble();
        scanner.nextLine();

        String sql = "SELECT id, tipo_movimentacao, descricao, valor, data_movimentacao " +
                "FROM " + SCHEMA + ".movimentacoes WHERE valor >= ? " +
                "ORDER BY valor DESC";

        exibirMovimentacoes(sql, valorMinimo);
    }

}

