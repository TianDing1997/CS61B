package bearmaps.hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private List<Vertex> solution;
    private double solutionWeight;
    private int numStatesExplore;
    private double explorationTime;
    private HashMap<Vertex, Double> distance;
    private HashMap<Vertex, Vertex> edgeTo;
    private DoubleMapPQ<Vertex> vertexPQ;
    private boolean targetGet = false;


    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        //AStarGraph<Vertex> Graph = input;
        solution = new ArrayList<>();
        distance = new HashMap<>();
        edgeTo = new HashMap<>();
        distance.put(start, 0.0);
        Stopwatch sw = new Stopwatch();
        vertexPQ = new DoubleMapPQ<>();
        vertexPQ.add(start, input.estimatedDistanceToGoal(start, end));
        while(vertexPQ.size() != 0){
            explorationTime = sw.elapsedTime();
            if(explorationTime <= timeout){
                Vertex p = vertexPQ.removeSmallest();
                //solution.add(p);
                //System.out.println(targetGet);
                if(p.equals(end)){
                    break;
                }
                else{
                    numStatesExplore++;
                    List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(p);
                    for(WeightedEdge<Vertex> edge : neighborEdges){
                        relax(edge, input, end);
                    }
                }
            }
            else{
                outcome = SolverOutcome.TIMEOUT;
                break;
            }
        }

        if(vertexPQ.size() == 0 && !targetGet){
            outcome = SolverOutcome.UNSOLVABLE;
        }
        else if(targetGet && (explorationTime <= timeout)){
            outcome = SolverOutcome.SOLVED;
            solutionWeight = distance.get(end);
            List<Vertex> reverseSolution = new ArrayList<>();
            Vertex vertex = end;
            reverseSolution.add(vertex);
            while(edgeTo.get(vertex) != null){
                reverseSolution.add(edgeTo.get(vertex));
                vertex = edgeTo.get(vertex);
            }
            for(int i = reverseSolution.size()-1; i>= 0; i--){
                solution.add(reverseSolution.get(i));
            }

        }
    }
    public SolverOutcome outcome() {
        return outcome;
    }
    public List<Vertex> solution() {
        return solution;
    }
    public double solutionWeight() {
        return solutionWeight;
    }
    public int numStatesExplored() {
        return numStatesExplore;
    }
    public double explorationTime() {
        return explorationTime;
    }

    private void relax(WeightedEdge<Vertex> e, AStarGraph<Vertex> input, Vertex end) {
        Vertex p = e.from();
        Vertex q = e.to();
        double weight = e.weight();
        //System.out.println(input.estimatedDistanceToGoal(q, end));
        if(q.equals(end)){
            targetGet = true;
        }
        if(!distance.containsKey(q)){
            distance.put(q, distance.get(p)+ weight);
            edgeTo.put(q, p);
            vertexPQ.add(q, distance.get(q) + input.estimatedDistanceToGoal(q, end));
            //System.out.println(distance.get(q) + input.estimatedDistanceToGoal(q, end));
        }
        else{
            if(( distance.get(p)+ weight) < distance.get(q)){
                edgeTo.put(q, p);
                distance.put(q, distance.get(p)+ weight);
                if(vertexPQ.contains(q)){
                    vertexPQ.changePriority(q, distance.get(q) + input.estimatedDistanceToGoal(q, end));
                    //System.out.println(distance.get(q) + input.estimatedDistanceToGoal(q, end));
                }
                else{
                    vertexPQ.add(q, distance.get(q) + input.estimatedDistanceToGoal(q, end));
                    //System.out.println(distance.get(q) + input.estimatedDistanceToGoal(q, end));
                }
            }
        }


    }
}

