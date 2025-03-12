# 🌳 Estruturas de Dados e Algoritmos

## 🌳 1. Árvores
Uma **árvore** é uma estrutura de dados hierárquica, onde cada nó tem um **pai** e pode ter vários **filhos**. A árvore é **não cíclica** e tem um nó raiz (o primeiro nó da estrutura).

### **Tipos de Árvores**
- **Árvore Geral**: Pode ter qualquer número de filhos.
- **Árvore Binária**: Cada nó pode ter **no máximo 2 filhos**.
- **Árvore de Busca Binária (BST)**: Os valores seguem a regra: **esquerda < raiz < direita**.

---

## 🌳 2. Árvores Binárias
Uma **árvore binária** é uma estrutura onde cada nó pode ter **até dois filhos**. Ela é usada para representar hierarquias e facilitar a busca de dados.

### **Propriedades das Árvores Binárias**
1. **Altura**: Número máximo de arestas entre a raiz e uma folha.
2. **Grau**: Número de filhos de um nó.
3. **Completa**: Todos os níveis, exceto o último, estão preenchidos.
4. **Cheia**: Todos os nós têm exatamente dois filhos ou nenhum.
5. **Balanceada**: A altura das subárvores difere no máximo por 1.

**Exemplo de uma árvore binária:**
```
      A
     / \
    B   C
   / \   \
  D   E   F
```

**Casos de uso:** Expressões matemáticas, árvores de decisão, jogos, árvores genealógicas.

### **Regras de Inserção em uma Árvore Binária**
A inserção segue algumas regras específicas, dependendo do tipo de árvore binária:

1. **Árvore Binária Genérica:** Um novo nó pode ser inserido em qualquer posição livre respeitando a hierarquia.
2. **Árvore Binária de Busca (BST):**
    - Se o valor for **menor** que a raiz, insere na **subárvore esquerda**.
    - Se for **maior**, insere na **subárvore direita**.
    - Continua esse processo recursivamente até encontrar uma posição livre.

**Exemplo de inserção na BST:**
Inserindo `15` na árvore abaixo:
```
      10
     /  \
    5   20
       /  \
      12   25
```
Resultado após a inserção:
```
      10
     /  \
    5   20
       /  \
      12   25
     /
    15
```

---

## 🔗 8. Grafos
Um **grafo** é um conjunto de **vértices (nós)** e **arestas (ligações)** que podem ou não ter direção.

### **Elementos de um Grafo**
- **Vértices (Nódos):** Os elementos do grafo.
- **Arestas:** As conexões entre os vértices.
- **Ponderação:** Caso as arestas tenham um peso associado.

### **Tipos de Grafos**
- **Grafo Simples:** Sem laços ou múltiplas arestas entre os vértices.
- **Grafo Dirigido:** As conexões têm direção (exemplo: ruas de mão única).
- **Grafo Não Dirigido:** Conexões sem direção específica.
- **Grafo Ponderado:** Cada aresta tem um peso associado (exemplo: distâncias entre cidades).
- **Grafo Conexo:** Existe um caminho entre quaisquer dois vértices.
- **Grafo Cíclico:** Contém pelo menos um ciclo.

### **Representação de Grafos**
1. **Matriz de Adjacência:** Uma matriz onde `matriz[i][j]` representa a existência (ou peso) de uma aresta entre os vértices `i` e `j`.
2. **Lista de Adjacência:** Uma estrutura onde cada vértice aponta para uma lista de vértices adjacentes.

### **Regras de Inserção em Grafos**
1. **Para inserir um novo vértice:**
    - Basta adicionar um novo nó ao conjunto de vértices.
2. **Para inserir uma nova aresta:**
    - Se o grafo for **não dirigido**, adicionamos uma conexão entre dois vértices `A - B`.
    - Se for **dirigido**, adicionamos uma conexão unidirecional `A → B`.
    - Se for **ponderado**, associamos um valor à conexão (`A -3→ B`).

**Exemplo de inserção em um grafo não dirigido:**

Antes da inserção:
```
A --- B
|     |
C     D
```
Se inserirmos uma aresta `C → D`:
```
A --- B
|     |
C --- D
```

### **Casos de Uso**
🚗 **Rotas (Google Maps):** Cálculo do caminho mais curto.  
🔗 **Redes Sociais:** Conexões entre pessoas.  
📡 **Redes de Computadores:** Modelagem de conexões entre servidores.

### **Algoritmos Importantes em Grafos**
1. **Busca em Largura (BFS):** Explora os vizinhos antes de ir mais fundo.
2. **Busca em Profundidade (DFS):** Explora ao máximo um caminho antes de retornar.
3. **Dijkstra:** Encontra o menor caminho de um nó para os outros.
4. **Floyd-Warshall:** Encontra todos os menores caminhos entre todos os nós.

Os grafos são essenciais para modelagem de sistemas complexos e otimizam processos como navegação e redes de comunicação.

Se precisar de mais detalhes ou código de implementação, me avise! 🚀


---

## 🔥 4. Árvores AVL
A **Árvore AVL** é uma **árvore de busca binária balanceada**. Sempre mantém a **diferença de altura dos subárvores ≤ 1**.

- Se um nó fica **desbalanceado**, fazemos **rotações** para restaurar o equilíbrio.

### **Exemplo**
```
      30
     /  \
    20   40
   /
  10
```
Se inserirmos `5`, fica desbalanceado → Fazemos **rotação direita**.

**Operações:**  
✅ **Busca:** O(log n)  
✅ **Inserção/Remoção:** O(log n)  
✅ **Sempre balanceada** → Melhor que BST tradicional.

---

## ⚫ 5. Árvores Red-Black
A **Árvore Red-Black** é outra árvore de busca binária balanceada, mas com regras específicas:
1. Cada nó é **vermelho** ou **preto**.
2. A raiz sempre é **preta**.
3. Um nó vermelho não pode ter filho vermelho.
4. Todo caminho da raiz até uma folha tem o mesmo número de nós pretos.

**Vantagens:**  
✅ Mais simples que AVL, pois **menos rotações são necessárias**.

**Usada em:** Sistemas de arquivos, bancos de dados.

---

## 🔺 6. Heaps
Um **Heap** é uma árvore **quase completa** usada para representar filas de prioridade.

### **Tipos de Heaps**
- **Heap Máximo**: O pai é **sempre maior** que os filhos.
- **Heap Mínimo**: O pai é **sempre menor** que os filhos.

Usado em **filas de prioridade**, **Dijkstra**, **algoritmos de ordenação (Heap Sort)**.

---

## 📏 7. Árvores de Segmento
Uma **Árvore de Segmento** é usada para responder a **consultas em intervalos** rapidamente.

- Suporta **modificações e buscas rápidas**.
- Muito usada para **problemas de programação competitiva**.

**Exemplo:**  
Se temos um array `[1, 3, 5, 7, 9, 11]` e queremos consultar a **soma de um intervalo**, a **árvore de segmento** responde em O(log n) em vez de O(n).

---

## 🚀 Conclusão
Essas são as **principais estruturas de dados avançadas**. Cada uma tem suas vantagens e casos de uso.

Se precisar de mais detalhes ou código de implementação, me avise! 🚀

---

========================================
---
---

# 🔢 Algoritmos de Ordenação (Sorting Algorithms)

A ordenação de dados é fundamental na computação. Os algoritmos de ordenação variam em eficiência, estabilidade e aplicabilidade. Vamos explorar os principais algoritmos de ordenação, explicando suas características, complexidade, estabilidade e exemplos práticos.

---

## 📌 Conceito de Estabilidade

Um **algoritmo de ordenação estável** mantém a ordem relativa dos elementos iguais. Já um **algoritmo instável** pode alterar essa ordem.

**Exemplo:**
Entrada: `[(A,2), (B,1), (C,2)]`

- **Ordenação Estável:** `[(B,1), (A,2), (C,2)]` (A e C mantêm a ordem original)
- **Ordenação Instável:** `[(B,1), (C,2), (A,2)]` (A e C trocam de posição)

---

## 📌 Conceito de Estabilidade

Um **algoritmo de ordenação estável** mantém a ordem relativa dos elementos iguais. Já um **algoritmo instável** pode alterar essa ordem.

**Exemplo:**
Entrada: `[(A,2), (B,1), (C,2)]`

- **Ordenação Estável:** `[(B,1), (A,2), (C,2)]` (A e C mantêm a ordem original)
- **Ordenação Instável:** `[(B,1), (C,2), (A,2)]` (A e C trocam de posição)

---

## 1️⃣ Selection Sort (Ordenação por Seleção)

### 🔹 Como Funciona?
O **Selection Sort** encontra o menor elemento do array e o coloca na posição correta, repetindo o processo até ordenar toda a estrutura.

### 🔹 Exemplo Visual

Entrada: `[29, 10, 14, 37, 13]`

```
1º Passo: [10, 29, 14, 37, 13]  (10 trocou com 29)
2º Passo: [10, 13, 14, 37, 29]  (13 trocou com 29)
3º Passo: [10, 13, 14, 37, 29]  (Nenhuma troca)
4º Passo: [10, 13, 14, 29, 37]  (29 trocou com 37)
```

### 🔹 Complexidade
- **Melhor caso:** O(n²)
- **Pior caso:** O(n²)
- **Caso médio:** O(n²)

### 🔹 Estável? ❌ **Não**
### 🔹 Caso de Uso:
- Quando o número de trocas precisa ser minimizado.
- Bom para listas pequenas onde a estabilidade não é necessária.

---

## 2️⃣ Insertion Sort (Ordenação por Inserção)

### 🔹 Como Funciona?
O **Insertion Sort** divide o array em uma parte ordenada e outra desordenada. A cada passo, insere um novo elemento na posição correta da parte ordenada.

### 🔹 Exemplo Visual
Entrada: `[5, 3, 8, 4, 2]`

```
1º Passo: [3, 5, 8, 4, 2]
2º Passo: [3, 5, 8, 4, 2]
3º Passo: [3, 4, 5, 8, 2]
4º Passo: [2, 3, 4, 5, 8]
```

### 🔹 Complexidade
- **Melhor caso:** O(n)
- **Pior caso:** O(n²)
- **Caso médio:** O(n²)

### 🔹 Estável? ✅ **Sim**
### 🔹 Caso de Uso:
- Pequenas quantidades de dados.
- Quando os dados já estão quase ordenados.
- Usado em algoritmos híbridos como o Timsort.

---

## 3️⃣ Bubble Sort (Ordenação por Bolha)

### 🔹 Como Funciona?
O **Bubble Sort** compara pares adjacentes e os troca se estiverem fora de ordem, repetindo até que não haja mais trocas.

### 🔹 Exemplo Visual
Entrada: `[5, 1, 4, 2, 8]`

```
1º Passo: [1, 5, 4, 2, 8]
2º Passo: [1, 4, 5, 2, 8]
3º Passo: [1, 4, 2, 5, 8]
4º Passo: [1, 2, 4, 5, 8]
```

### 🔹 Complexidade
- **Melhor caso:** O(n)
- **Pior caso:** O(n²)
- **Caso médio:** O(n²)

### 🔹 Estável? ✅ **Sim**
### 🔹 Caso de Uso:
- Para aprendizado básico sobre ordenação.
- Quando há poucos elementos.

---

## 4️⃣ Quick Sort (Ordenação Rápida)

### 🔹 Como Funciona?
O **Quick Sort** escolhe um pivô e reorganiza o array de forma que os menores fiquem à esquerda e os maiores à direita, aplicando recursão.

### 🔹 Exemplo Visual
Entrada: `[10, 80, 30, 90, 40, 50, 70]`

```
1º Passo: Pivô = 50 → [10, 30, 40, 50, 90, 80, 70]
2º Passo: Aplica recursão na esquerda e direita
```

### 🔹 Complexidade
- **Melhor caso:** O(n log n)
- **Pior caso:** O(n²) (se pivô ruim escolhido)
- **Caso médio:** O(n log n)

### 🔹 Estável? ❌ **Não**
### 🔹 Caso de Uso:
- Grandes conjuntos de dados.
- Quando rapidez é essencial.

---

## 5️⃣ Merge Sort (Ordenação por Fusão)

### 🔹 Como Funciona?
O **Merge Sort** divide o array ao meio, ordena recursivamente e depois combina as metades ordenadas.

### 🔹 Exemplo Visual
Entrada: `[38, 27, 43, 3, 9, 82, 10]`

```
Divide em: [38, 27, 43] e [3, 9, 82, 10]
Ordena e mescla recursivamente.
```

### 🔹 Complexidade
- **Melhor caso:** O(n log n)
- **Pior caso:** O(n log n)
- **Caso médio:** O(n log n)

### 🔹 Estável? ✅ **Sim**
### 🔹 Caso de Uso:
- Ordenação externa (grandes volumes de dados).
- Quando se precisa garantir estabilidade.
- Utilizado em sistemas que manipulam grandes arquivos.

---

Agora, todos os algoritmos possuem explicações detalhadas, exemplos visuais e casos de uso! 🚀

---

## 6️⃣ Heap Sort (Ordenação por Heap)

### 🔹 O que é um Heap?
Um **Heap** é uma estrutura de dados baseada em árvore binária que mantém a propriedade de heap:
- **Heap Máximo:** O pai é sempre maior ou igual aos filhos.
- **Heap Mínimo:** O pai é sempre menor ou igual aos filhos.

**Exemplo de um Heap Máximo:**
```
       50
      /  \
     30   40
    /  \   /
   10   20 35
```
Aqui, cada pai é maior que seus filhos, garantindo a propriedade de heap máximo.

### 🔹 Como Funciona o Heap Sort?
1. Constrói um **Heap Máximo** a partir do array original.
2. O maior elemento (raiz do heap) é movido para o final do array.
3. O heap é reorganizado (heapify) para restaurar a propriedade de heap.
4. Repete o processo até que o array esteja ordenado.

### 🔹 Exemplo Visual com Passo a Passo
Entrada: `[4, 10, 3, 5, 1]`

1º Passo: **Construção do Heap Máximo**
```
      10
     /  \
    5    3
   / \
  4   1
```
2º Passo: **Remove o maior (10), move o último nó (1) para a raiz e faz `heapify`:**
```
      1
     /  \
    5    3
   /
  4
```

3º Passo: **Aplicando `heapify`, 5 sobe para o topo:**
```
      5
     /  \
    1    3
   /
  4
```

4º Passo: **Remove 5, coloca 4 na raiz e faz `heapify`:**
```
      4
     /  \
    1    3
```

5º Passo: **Remove 4, coloca 3 na raiz e faz `heapify`:**
```
      3
     /
    1
```

6º Passo: **Remove 3 e depois 1, terminando a ordenação.**

---

### 🔹 Resultado Final (Array Ordenado)
Após todas as remoções, os elementos ficam ordenados no array:
```
[1, 3, 4, 5, 10]
```

✅ **Conclusão:** O Heap Sort **não reinserta os elementos removidos na árvore, mas os coloca diretamente na posição final no array ordenado.** 🚀

### 🔹 Complexidade
- **Melhor caso:** O(n log n)
- **Pior caso:** O(n log n)
- **Caso médio:** O(n log n)

### 🔹 Estável? ❌ **Não** (trocas arbitrárias podem mudar a ordem de elementos iguais)

### 🔹 Aplicações do Heap Sort
O Heap Sort é utilizado em situações onde a eficiência e o uso de memória são cruciais, tais como:
1. **Filas de Prioridade:** Algoritmos como Dijkstra para encontrar o menor caminho em grafos.
2. **Gerenciamento de Processos:** Agendamento de tarefas em sistemas operacionais.
3. **Sistemas de Busca:** Algoritmos de seleção, como encontrar os k maiores elementos.
4. **Processamento de Streaming de Dados:** Usado quando os dados não cabem na memória e precisam ser processados em lotes.

O Heap Sort é uma escolha eficiente para grandes volumes de dados, pois sua complexidade de tempo **O(n log n)** é garantida independentemente da entrada, diferentemente do Quick Sort que pode ter pior caso **O(n²)**.

---

Agora, todos os algoritmos possuem explicações detalhadas, exemplos visuais e casos de uso! 🚀

