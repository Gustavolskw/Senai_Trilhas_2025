package Casos_de_uso;

import java.util.*;

class BPlusTree {
    private static final int ORDER = 4; // Grau mínimo (definir conforme necessidade)
    private Node root;

    // Nó interno da árvore
    abstract class Node {
        List<String> keys = new ArrayList<>();
        abstract boolean isLeaf();
    }

    // Nó interno (guarda chaves e ponteiros)
    class InternalNode extends Node {
        List<Node> children = new ArrayList<>();

        boolean isLeaf() { return false; }
    }

    // Nó folha (guarda arquivos/diretórios)
    class LeafNode extends Node {
        Map<String, String> files = new HashMap<>(); // Nome do arquivo -> Conteúdo
        LeafNode next; // Ponteiro para próximo nó folha

        boolean isLeaf() { return true; }
    }

    // Construtor
    public BPlusTree() {
        root = new LeafNode();
    }

    // Inserção de arquivo
    public void insert(String filename, String content) {
        LeafNode leaf = findLeafNode(filename);
        leaf.files.put(filename, content);
        if (!leaf.keys.contains(filename)) {
            leaf.keys.add(filename);
            Collections.sort(leaf.keys);
        }
        splitIfNeeded(leaf);
    }

    // Busca por arquivo
    public String search(String filename) {
        LeafNode leaf = findLeafNode(filename);
        return leaf.files.getOrDefault(filename, null);
    }

    // Remoção de arquivo
    public void remove(String filename) {
        LeafNode leaf = findLeafNode(filename);
        if (leaf.files.containsKey(filename)) {
            leaf.files.remove(filename);
            leaf.keys.remove(filename);
        }
    }

    // Encontra o nó folha adequado
    private LeafNode findLeafNode(String filename) {
        Node current = root;
        while (!current.isLeaf()) {
            InternalNode internal = (InternalNode) current;
            int i = 0;
            while (i < internal.keys.size() && filename.compareTo(internal.keys.get(i)) > 0) i++;
            current = internal.children.get(i);
        }
        return (LeafNode) current;
    }

    // Divide o nó se necessário
    private void splitIfNeeded(LeafNode leaf) {
        if (leaf.keys.size() < ORDER) return;

        LeafNode newLeaf = new LeafNode();
        int mid = ORDER / 2;

        newLeaf.keys.addAll(leaf.keys.subList(mid, leaf.keys.size()));
        newLeaf.files.putAll(leaf.files);
        leaf.keys.subList(mid, leaf.keys.size()).clear();

        newLeaf.next = leaf.next;
        leaf.next = newLeaf;
    }


    public static void main(String[] args) {
        BPlusTree fileSystem = new BPlusTree();
        long start, end;

        // Teste de inserção
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            fileSystem.insert("file" + i + ".txt", "Conteúdo do arquivo " + i);
        }
        end = System.nanoTime();
        System.out.println("Tempo de inserção: " + (end - start) / 1e6 + " ms");

        // Teste de busca
        start = System.nanoTime();
        String content = fileSystem.search("file500.txt");
        end = System.nanoTime();
        System.out.println("Busca pelo arquivo file500.txt: " + content);
        System.out.println("Tempo de busca: " + (end - start) / 1e6 + " ms");

        // Teste de remoção
        start = System.nanoTime();
        fileSystem.remove("file500.txt");
        end = System.nanoTime();
        System.out.println("Arquivo file500.txt removido.");
        System.out.println("Tempo de remoção: " + (end - start) / 1e6 + " ms");

        // Verificar se foi realmente removido
        content = fileSystem.search("file500.txt");
        System.out.println("Busca após remoção: " + (content == null ? "Arquivo não encontrado" : content));
    }
}

