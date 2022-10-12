public class Celula {
        public int id;
        public long pos;
        public Celula prox;
        public boolean deleted;
  
        public Celula(int id,long pos,char deleted) {
  
          this.id = id;
          this.pos = pos;
          this.deleted = (deleted == '*') ? true : false;
          prox = new Celula();
        }
  
        public Celula(int id,long pos) {
  
          this.id = id;
          this.pos = pos;
          this.deleted = false;
          prox = new Celula();
        }
  
        public Celula() {
  
          id = 0;
          pos = 0;
          deleted = false;
          prox = null;
        }
}

class Auxiliar{
    public Celula inicio;

  public Auxiliar() {
    inicio = new Celula();
  }

    public long find(int id) {

       Celula i = null;
       long resp = -1;
       boolean stop = false;
       for (i = inicio.prox; i != null && stop == false; i = i.prox) {

        if ( i.id == id) {
           
            if (!i.deleted) {
              resp = i.pos;
            }
              stop = true;
        }
      }
        return resp;

    } 

}