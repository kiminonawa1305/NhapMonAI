package agent_matrix;

import agent_matrix.Environment.*;

import java.util.LinkedList;

public class TestSimpleReflexAgent {
    public static void main(String[] args) {
        Environment env = new Environment(5 , 4);
        Agent agent = new Agent(new AgentProgram());
        if(env.addAgent(agent)){
            Application application = new Application(env);
        }else{
            System.out.println("Không thể thêm Agent được!");
        }
    }
}
