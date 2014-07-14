# Closeness Centrality

## Exercise

In this challenge, suppose we are looking to do social network analysis
for prospective customers. We want to extract from their social network
a metric called "closeness centrality".

Centrality metrics try to approximate a measure of influence of an
individual within a social network. The distance between any two
vertices is their shortest path. The *farness* of a given vertex *v* is
the sum of all distances from each vertex to *v*. Finally, the
*closeness* of a vertex *v* is the inverse of the *farness*.

The first part of the challenge is to rank the vertices in a given graph
by their *closeness*. The graph is provided in the attached file; each
line of the file consists of two vertex names separated by a single
space, representing an edge between those two nodes.

The second part of the challenge is to plug your algorithm on Facebook's
API and rank the centrality of the social graph extracted from one user
and his list of friends. A good start might be the mutual friends API.
In order to exercise your code, you can use Facebook’s sandbox Test
Users feature (you can find it under Roles).

## Proposed Solution

* For a simple approach, it's assumed that the graph object is an
_Undirected Graph_. It uses an _Adjacency List_ to store the edges.
* Implement _Dijkstra's algorithm_ for the shortest path.
* Calculate the _Closeness Centrality_.

## Running the sample code

### Requirements

* JDK 1.7
* Scala 2.11.1
* SBT 0.13.3

### Edges file parsing and Facebook Graph

Export your `ACCESS_TOKEN` and `APP_SECRET` into the environment and
then type `sbt run`.

An easier to do this should be to create a `.env`
file in the project root (as exemplified bellow) and then just type
`source .env && sbt run`.

```
ACCESS_TOKEN="..."
APP_SECRET="..."
```

### Tests

Just type `sbt test`.

## References

* [Closeness Centrality](http://en.wikipedia.org/wiki/Centrality#Closeness_centrality)
* [Shortest path problem](http://en.wikipedia.org/wiki/Shortest_path_problem)
* [Facebook API - Friends](https://developers.facebook.com/docs/graph-api/reference/v2.0/user/friends)
* [Facebook API - Mutual friends](https://developers.facebook.com/docs/graph-api/reference/v2.0/user.context/mutual_friends)
