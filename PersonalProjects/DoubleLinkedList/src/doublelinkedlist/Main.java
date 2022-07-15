

package doublelinkedlist;

/**
 *
 * @author Randal Obringer
 */
public class Main {
    public static void main(String[] args) {
        // TODO code application logic here
        DoubleLinkedList myList = new DoubleLinkedList();
        myList.add(0);
        myList.add(6);
        myList.add(3);
        myList.add(-1);
        myList.add(5);
        myList.add(10);
        myList.add(30);
        myList.add(20);
        myList.showAll();
        myList.showAllReversed();
        myList.delete(-1);
        myList.delete(30);
        myList.delete(3);
        myList.showAll();
        myList.showAllReversed();
        System.out.println(myList.getSize());
    }
    
}
