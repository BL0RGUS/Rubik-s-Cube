package JavaFx.CubeModel;


//this is  a class used to represent the value of a sticker on the cube,
//it is also used to store the value of a full face as a 64 bit integer.
public class Face {

    private long face;

    public Face(long face){
        this.face = face;
    }

    public int getFace(){
        return (int)this.face;
    }


    public long getWholeFace(){
        return this.face;
    }


    public void setFace(long n){
        this.face = n;
    }

}
