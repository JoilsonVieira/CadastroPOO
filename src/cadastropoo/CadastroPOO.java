package cadastropoo;

import java.util.Scanner;
import model.*;

public class CadastroPOO {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
        
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n==============================");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Exibir pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("6 - Salvar dados");
            System.out.println("7 - Recuperar dados");
            System.out.println("0 - Sair");
            System.out.println("==============================");
            System.out.print("Opcao: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um numero valido.");
                continue;
            }

            switch (opcao) {
                case 1: // Incluir
                    System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
                    String tipoIncluir = scanner.nextLine().toUpperCase();
                    if (tipoIncluir.equals("F")) {
                        System.out.print("Digite o ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Idade: ");
                        int idade = Integer.parseInt(scanner.nextLine());
                        repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
                        System.out.println("Pessoa Fisica incluida com sucesso.");
                    } else if (tipoIncluir.equals("J")) {
                        System.out.print("Digite o ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CNPJ: ");
                        String cnpj = scanner.nextLine();
                        repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
                        System.out.println("Pessoa Juridica incluida com sucesso.");
                    }
                    break;

                case 2: // Alterar
                    System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
                    String tipoAlterar = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o ID da pessoa a alterar: ");
                    int idAlterar = Integer.parseInt(scanner.nextLine());

                    if (tipoAlterar.equals("F")) {
                        PessoaFisica pf = repoFisica.obter(idAlterar);
                        if (pf != null) {
                            System.out.println("Dados atuais:");
                            pf.exibir();
                            System.out.print("Novo Nome: ");
                            pf.setNome(scanner.nextLine());
                            System.out.print("Novo CPF: ");
                            pf.setCpf(scanner.nextLine());
                            System.out.print("Nova Idade: ");
                            pf.setIdade(Integer.parseInt(scanner.nextLine()));
                            repoFisica.alterar(pf);
                            System.out.println("Alteracao realizada.");
                        } else {
                            System.out.println("ID nao encontrado.");
                        }
                    } else if (tipoAlterar.equals("J")) {
                        PessoaJuridica pj = repoJuridica.obter(idAlterar);
                        if (pj != null) {
                            System.out.println("Dados atuais:");
                            pj.exibir();
                            System.out.print("Novo Nome: ");
                            pj.setNome(scanner.nextLine());
                            System.out.print("Novo CNPJ: ");
                            pj.setCnpj(scanner.nextLine());
                            repoJuridica.alterar(pj);
                            System.out.println("Alteracao realizada.");
                        } else {
                            System.out.println("ID nao encontrado.");
                        }
                    }
                    break;

                case 3: // Excluir
                    System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
                    String tipoExcluir = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o ID a excluir: ");
                    int idExcluir = Integer.parseInt(scanner.nextLine());
                    if (tipoExcluir.equals("F")) {
                        repoFisica.excluir(idExcluir);
                        System.out.println("Pessoa Fisica excluida.");
                    } else if (tipoExcluir.equals("J")) {
                        repoJuridica.excluir(idExcluir);
                        System.out.println("Pessoa Juridica excluida.");
                    }
                    break;

                case 4: // Exibir pelo ID
                    System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
                    String tipoExibir = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o ID: ");
                    int idExibir = Integer.parseInt(scanner.nextLine());
                    if (tipoExibir.equals("F")) {
                        PessoaFisica pf = repoFisica.obter(idExibir);
                        if (pf != null) pf.exibir(); else System.out.println("Nao encontrado.");
                    } else if (tipoExibir.equals("J")) {
                        PessoaJuridica pj = repoJuridica.obter(idExibir);
                        if (pj != null) pj.exibir(); else System.out.println("Nao encontrado.");
                    }
                    break;

                case 5: // Exibir todos
                    System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
                    String tipoTodos = scanner.nextLine().toUpperCase();
                    if (tipoTodos.equals("F")) {
                        for (PessoaFisica pf : repoFisica.obterTodos()) {
                            pf.exibir();
                            System.out.println("-----------------");
                        }
                    } else if (tipoTodos.equals("J")) {
                        for (PessoaJuridica pj : repoJuridica.obterTodos()) {
                            pj.exibir();
                            System.out.println("-----------------");
                        }
                    }
                    break;

                case 6: // Salvar
                    System.out.print("Digite o prefixo dos arquivos: ");
                    String prefixoSalvar = scanner.nextLine();
                    try {
                        repoFisica.persistir(prefixoSalvar + ".fisica.bin");
                        repoJuridica.persistir(prefixoSalvar + ".juridica.bin");
                        System.out.println("Dados salvos com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar: " + e.getMessage());
                    }
                    break;

                case 7: // Recuperar
                    System.out.print("Digite o prefixo dos arquivos: ");
                    String prefixoRec = scanner.nextLine();
                    try {
                        repoFisica.recuperar(prefixoRec + ".fisica.bin");
                        repoJuridica.recuperar(prefixoRec + ".juridica.bin");
                        System.out.println("Dados recuperados com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro ao recuperar: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Finalizando...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        }
        scanner.close();
    }
}