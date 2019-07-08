# Mini_Project-Random_Matching_graphs<br /><br />

## ABOUT<br />
The paper’s algorithm is known as randomized algorithm and have an expectation and high probability of O(nlogn) . The algorithm performs an appropriately truncated random walk on a modified graph to successively find augmenting path.

## ALGORITHM<br />
First, in order to find perfect match in the graph, we will introduce the Transform graph: Given any (partial) matching M in G, the algorithm implicitly constructs a directed graph, called the matching graph of M. Transformed H is with addition of source and sink nodes, and contraction of each two nodes contained by an edge in M, the partial match.
Second, we will define a Truncated-Walk in a graph, as a function who create a path from any node (initially source) to one of his neighbors (hopefully, lastly sink), in our Transformed graph. Bj acting as indicator for maximum steps.
Lastly, our Randomized part of the algorithm is centralized in Sample-Out-Edge, which will return in a random fashion the neighbor to go to in Truncated-Walk.

## IMPLEMENTATION<br />
1. BipartiteGraph:<br />
  - Pnode/Qnodes –arrays that holds a Node object in every entry (and in the Node class the linked-neighbor list).<br />
  - Edges – list of graph edges. (used mainly for well-constructed checks)<br />
2. Node:<br />
  - Main purpose – holds the neighbor list.<br />
  - Holds few more private fields to ease the algorithm flows.
3. Edge:<br />
  - Represent an edge in the graph.<br />
  - 3 fields – from (node index), to (node index) and ID.<br />
  - Mainly is in algorithm run.<br />
4. RundomAlgorithm:<br />
  - Represent a new run of the algorithm on a certain graph.<br />
  - In each run, we hold current partial path of truncated-walk named p.<br />
  - In each run, we hold current partial match in the graph named Mj.<br />
  - The run holds his P nodes, Q nodes, and number on nodes in graph.<br />
  - Mainly holds an instance of a run of the algorithm on a graph.<br />
5. Main:<br />
  - Use to run our algorithm tests.
  <br /><br />
## HOW TO RUN<br />
* To run our project with .jar:<br />
  1)	Open the cmd<br />
  2)	Go to the path where the PerfectMatchingBipartiteGraph.jar located<br />
  3)	Command in cmd to run the project:<br />
	java -jar PerfectMatchingBipartiteGraph.jar<br />

  4)	Choose a test option between 1-4,<br />
	Choose number of vertices (per group of nodes - there are 2 groups because we use BipartiteGraph)<br />
	Choose numer of neighbors per node (d-regular)<br />
	Choose number of runs	<br />

	NOTE - if you choose randomized constructor for fluently running please choose d-regular number <6<br />
	
* to run normaly:<br />
	1) open the 5 classes<br />
	2) run the Main function in Main class

