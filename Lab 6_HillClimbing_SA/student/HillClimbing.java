package student;

public class HillClimbing {
    public void execute(Node currentNode){
        currentNode.displayBoard();

        while(true){
            Node child = currentNode.getBestCandidate();
            if(child.getH() < currentNode.getH()){
                currentNode = child;
                currentNode.displayBoard();
            }else{
                break;
            }
        }
    }

    public static void main(String[] args) {
        HillClimbing hillClimbing = new HillClimbing();
        hillClimbing.execute(new Node());
    }
}
