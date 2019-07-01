//	package ouc.cs;

	import java.util.Vector;

	public class VectorTraverse {
		public void overview(Vector<?> vector){
			for(Object o:vector){
				System.out.print(o);
			}
		}
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Vector<Integer> v1 = new Vector<>();
			for(int i=0;i<9;i++){
				v1.addElement(i);
			}
			Vector<Character> v2 = new Vector<>();
			for(char i='A';i<'Z';i++){
				v2.add(i);
			}
			VectorTraverse vectorTraverse = new VectorTraverse();
			vectorTraverse.overview(v1);
			System.out.println("\n");
			vectorTraverse.overview(v2);
			
		}

	}
