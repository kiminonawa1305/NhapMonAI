package agent_matrix;

import agent_matrix.Environment.*;

import java.util.LinkedList;

public class TestSimpleReflexAgent {
    public static void main(String[] args) {
//        LocationState[][] matrixState = {
//                {LocationState.DIRTY, LocationState.DIRTY, LocationState.CLEAN, LocationState.CLEAN},
//                {LocationState.CLEAN, LocationState.CLEAN,LocationState.CLEAN, LocationState.CLEAN},
//                {LocationState.OBSTACLES, LocationState.CLEAN,LocationState.OBSTACLES, LocationState.CLEAN},
//                {LocationState.CLEAN, LocationState.CLEAN,LocationState.CLEAN, LocationState.CLEAN},
//                {LocationState.CLEAN, LocationState.OBSTACLES,LocationState.CLEAN, LocationState.DIRTY}};
        Environment env = new Environment(5 , 4);
        Agent agent = new Agent(new AgentProgram());
        if(env.addAgent(agent)){
//            env.stepUntilDone();
            Application application = new Application(env);
        }else{
            System.out.println("Không thể thêm Agent được!");
        }




//        Graph graph = new Graph(matrixState);
//        graph.procedureFloyd();
//        LinkedList<Integer> path = graph.getPathFromProcedureFloyd(2, 20);
//        System.out.println(path);
//        System.out.println(path.get(path.size() - 2));
    }
}
