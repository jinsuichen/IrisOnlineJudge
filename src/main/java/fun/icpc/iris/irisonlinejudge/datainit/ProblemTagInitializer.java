package fun.icpc.iris.irisonlinejudge.datainit;

import fun.icpc.iris.irisonlinejudge.domain.entity.ProblemTagCatalogEntity;
import fun.icpc.iris.irisonlinejudge.domain.entity.ProblemTagEntity;
import fun.icpc.iris.irisonlinejudge.repo.ProblemTagCatalogRepository;
import fun.icpc.iris.irisonlinejudge.repo.ProblemTagRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProblemTagInitializer implements CommandLineRunner {

    private final ProblemTagRepository problemTagRepository;

    private final ProblemTagCatalogRepository problemTagCatalogRepository;

    @Override
    @Transactional
    public void run(String... args) {

        if(problemTagCatalogRepository.count() != 0 || problemTagRepository.count() != 0) {
            return;
        }

        List<String> combinatoricsTags = List.of(
                "Combinatorics",
                "Permutations and Combinations",
                "Generating Function",
                "Polya's Theorem",
                "Permutation Group",
                "Lucas's Theorem",
                "Catalan Number",
                "Inclusion-Exclusion Principle and Pigeonhole Principle",
                "Steiner Tree",
                "Stirling Numbers",
                "Binomial Theorem",
                "Burnside's Lemma",
                "Prufer Sequence"
        );

        List<String> linearAlgebraTags = List.of(
                "Linear Algebra",
                "Matrix Inverse",
                "Matrix Multiplication",
                "Linear Basis",
                "Gaussian Elimination"
        );

        List<String> graphTheoryTags = List.of(
                "Graph Theory",
                "Lowest Common Ancestor (LCA)",
                "Maximum Clique",
                "Shortest Path",
                "Graph Traversal",
                "Topological Sorting",
                "Spanning Tree",
                "Flow Network",
                "Eulerian Circuit",
                "Connectivity",
                "Network Flow",
                "Graph Matching",
                "Bipartite Graph"
        );

        List<String> computationalGeometryTags = List.of(
                "Computational Geometry",
                "Three-Dimensional Computational Geometry",
                "Convex Hull",
                "Half-plane Intersection",
                "Minimum Circle Covering"
        );

        List<String> numberTheoryTags = List.of(
                "Number Theory",
                "Fermat's Little Theorem",
                "Discrete Logarithm",
                "Prime Factorization",
                "Euler's Totient Function",
                "GCD and Extended GCD",
                "Sieve Methods",
                "Primality Testing",
                "Multiplicative Inverse",
                "Euler's Theorem",
                "Chinese Remainder Theorem",
                "Multiplicative Function"
        );

        List<String> mathematicsTags = List.of(
                "Mathematics",
                "Numerical Integration",
                "Number Base Conversion",
                "Polynomials",
                "Fast Exponentiation",
                "High Precision",
                "Baby Step Giant Step (BSGS) Algorithm",
                "Fast Fourier Transform (FFT) / Number Theoretic Transform (NTT) / Fast Walsh Transform (FWT)",
                "Game Theory",
                "Probability and Expectation",
                "Mobius Inversion",
                "Generating Functions",
                "Lagrange Interpolation"
        );

        List<String> stringAlgorithmsTags = List.of(
                "String Algorithms",
                "Aho-Corasick Automaton (AC Automaton)",
                "KMP and Extended KMP",
                "Trie Tree (Prefix Tree)",
                "Suffix Array (SA)",
                "Suffix Automaton (SAM)",
                "Manacher",
                "Suffix Tree",
                "Trie Graph",
                "String Hashing",
                "Boyer-Moore Algorithm (BM Algorithm)",
                "Palindrome Automaton (PAM)"
        );

        List<String> languageTags = List.of(
                "Language"
        );

        List<String> dataStructureTags = List.of(
                "Data Structures",
                "Treap",
                "Block List",
                "Link-Cut Tree (LCT)",
                "Heap / Priority Queue",
                "Stack",
                "Queue",
                "Balanced Tree",
                "Disjoint-Set Union (DSU) / Union-Find",
                "Tree on Tree",
                "Segment Tree",
                "Monotonic Queue / Monotonic Stack",
                "Tree",
                "Heavy-Light Decomposition (HLD)",
                "Fenwick Tree / Binary Indexed Tree (BIT)",
                "KD-Tree",
                "Sparse Table (ST)",
                "Block",
                "Mo's Algorithm",
                "Cactus Graph",
                "Leftist Tree / Mergeable Heap",
                "Standard Template Library (STL)",
                "Linked List",
                "Splay Tree",
                "DSU on Tree",
                "Dancing Links / DLX",
                "Range Minimum Query (RMQ)",
                "DFS Order",
                "Link-Cut Tree"
        );

        List<String> persistentDataStructureTags = List.of(
                "Persistent Data Structures",
                "Persistent Trie Tree",
                "Persistent Heap",
                "Persistent Balanced Tree",
                "Persistent Segment Tree"
        );

        List<String> greedyTags = List.of(
                "Greedy"
        );


        List<String> recursionTags = List.of(
                "Recursion"
        );

        List<String> constructTags = List.of(
                "Construct"
        );

        List<String> sortingTags = List.of(
                "Sorting",
                "Merge"
        );

        List<String> exponentiationBySquaringTags = List.of(
                "Exponentiation by Squaring"
        );

        List<String> hashingTags = List.of(
                "Hashing"
        );

        List<String> recurrenceTags = List.of(
                "Recurrence"
        );

        List<String> divideAndConquerTags = List.of(
                "Divide and Conquer",
                "Binary Search",
                "CDQ Divide and Conquer",
                "Tree Divide and Conquer",
                "0-1 Fractional Programming",
                "Global Binary Search",
                "Ternary Search"
        );

        List<String> enumerationTags = List.of(
                "Enumeration",
                "Difference (Prefix Sum)",
                "Bitmask Enumeration",
                "Prefix Sum",
                "Bitwise Operations",
                "Two Pointers (Sliding Window)",
                "Sweep Line",
                "Discretization",
                "Binary Enumeration"
        );

        List<String> searchTags = List.of(
                "Search",
                "Iterative Deepening A* Search (IDA*)",
                "Iterative Deepening",
                "Breadth-First Search (BFS)",
                "Depth-First Search (DFS)",
                "A* Search",
                "Search Pruning"
        );

        List<String> cantorExpansionTags = List.of("Cantor Expansion");
        List<String> simulationTags = List.of("Simulation");
        List<String> simulatedAnnealingTags = List.of("Simulated Annealing");

        List<String> dynamicProgrammingTags = List.of(
                "Dynamic Programming",
                "Linear DP",
                "DP Optimization",
                "Knapsack Problem",
                "Plug DP",
                "Bitmask DP",
                "Digit DP",
                "Tree DP",
                "Interval DP",
                "Probability DP",
                "Tree-DP with Cycles",
                "Memoization (Memorization) Search"
        );


        List<String> garsiaWachsAlgorithmTags = List.of("Garsia-Wachs Algorithm");
        List<String> thoughtTags = List.of("Thought");
        List<String> bruteforceTags = List.of("Bruteforce");

        List<List<String>> catalogs = List.of(
                combinatoricsTags,
                linearAlgebraTags,
                graphTheoryTags,
                computationalGeometryTags,
                numberTheoryTags,
                mathematicsTags,
                stringAlgorithmsTags,
                languageTags,
                dataStructureTags,
                persistentDataStructureTags,
                greedyTags,
                recursionTags,
                constructTags,
                sortingTags,
                exponentiationBySquaringTags,
                hashingTags,
                recurrenceTags,
                divideAndConquerTags,
                enumerationTags,
                searchTags,
                cantorExpansionTags,
                simulationTags,
                simulatedAnnealingTags,
                dynamicProgrammingTags,
                garsiaWachsAlgorithmTags,
                thoughtTags,
                bruteforceTags
        );

        // Init problem tag catalog
        problemTagCatalogRepository.saveAll(
                ListUtils.union(
                        catalogs.stream()
                                .map(catalog -> ProblemTagCatalogEntity.builder().name(catalog.get(0)).build()).toList(),
                        Arrays.asList(
                                ProblemTagCatalogEntity.builder().name("Others").build(),
                                ProblemTagCatalogEntity.builder().name("Uncategorized").build()
                        )
                )
        );


        // Init problem tag
        for (List<String> tags : catalogs) {
            ProblemTagCatalogEntity problemTagCatalogEntity = problemTagCatalogRepository.findByName(tags.get(0)).orElseThrow();
            tags.forEach(tag -> problemTagRepository.save(
                    ProblemTagEntity.builder()
                            .name(tag)
                            .catalog(problemTagCatalogEntity)
                            .build()
            ));
        }

    }
}
