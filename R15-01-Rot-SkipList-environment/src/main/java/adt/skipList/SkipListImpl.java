package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

   protected SkipListNode<T> root;
   protected SkipListNode<T> NIL;

   protected int maxHeight;

   protected double PROBABILITY = 0.5;

   public SkipListImpl(int maxHeight) {
      this.maxHeight = maxHeight;
      root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
      NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
      connectRootToNil();
   }

   /**
    * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
    * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
    * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
    * metodo deve conectar apenas o forward[0].
    */
   private void connectRootToNil() {
      for (int i = 0; i < maxHeight; i++) {
         root.forward[i] = NIL;
      }
   }

   @Override
   public void insert(int key, T newValue, int height) {
      if (newValue != null) {
         SkipListNode<T>[] atualiza = new SkipListNode[height];
         SkipListNode<T> aux = root;

         for (int i = height - 1; i >= 0; i--) {
            while (aux.getForward(i) != null && aux.getForward(i).getKey() < key) {
               aux = aux.getForward(i);
            }
            atualiza[i] = aux;
         }

         aux = aux.getForward(0);

         if (aux.getKey() == key) {
            aux.setValue(newValue);
         } else {
            ajust(height, atualiza);
            aux = new SkipListNode<T>(key, height, newValue);
            changeRefers(height, atualiza, aux);
         }
      }
   }

   private void changeRefers(int height, SkipListNode<T>[] atualiza, SkipListNode<T> aux) {
      for (int i = 0; i < height; i++) {

         if (atualiza[i].getForward(i) == null) {
            aux.getForward()[i] = NIL;
         } else {
            aux.forward[i] = atualiza[i].forward[i];
            atualiza[i].forward[i] = aux;
         }

      }

   }

   private void ajust(int height, SkipListNode<T>[] atualiza) {
      if (height > this.maxHeight) {
         for (int i = this.maxHeight; i < height; i++) {
            root.getForward()[i] = NIL;
         }
         this.maxHeight = height;
      }
   }

   @Override
   public void remove(int key) {
      SkipListNode[] array = new SkipListNode[this.maxHeight];

      SkipListNode<T> aux = this.root;

      for (int i = maxHeight - 1; i >= 0; i--) {
         if (aux.forward[i] != this.NIL) {
            while (aux.forward[i].value != null && aux.forward[i].key < key)
               aux = aux.forward[i];
         }
         array[i] = aux;
      }
      aux = aux.getForward()[0];

      if (aux.key == key) {

         for (int i = 0; i < maxHeight; i++) {
            if (array[i].getForward()[i] != aux) {
               break;
            }
            array[i].getForward()[i] = aux.getForward()[i];
         }
      }
   }

   @Override
   public int height() {
      int height = this.maxHeight - 1;
      while (height >= 0 && root.getForward(height) == NIL) {
         if (height == 0) {
            height--;
            break;
         } else {
            height--;
         }
      }
      return height + 1;
   }

   @Override
   public SkipListNode<T> search(int key) {
      SkipListNode<T> aux = this.root;

      for (int i = maxHeight - 1; i >= 0; i--) {
         while (aux.getForward(i) != null && aux.getForward(i).getKey() < key) {
            aux = aux.getForward(i);
         }
      }

      SkipListNode<T> saida = aux.getForward(0);

      if (saida.getKey() != key) {
         saida = null;
      }

      return saida;
   }

   @Override
   public int size() {
      int count = 0;
      SkipListNode<T> auxNode = this.root.getForward(0);
      while (auxNode != NIL) {
         count++;
         auxNode = auxNode.getForward(0);
      }

      return count;
   }

   @Override
   public SkipListNode<T>[] toArray() {
      int tam = size() + 2;

      SkipListNode<T>[] saida = new SkipListNode[tam];
      SkipListNode<T> aux = this.root;

      int index = 0;
      while (index < tam) {
         saida[index] = aux;
         index++;
         aux = aux.getForward(0);
      }
      return saida;
   }
}
