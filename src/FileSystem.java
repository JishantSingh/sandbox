import com.sun.corba.se.impl.encoding.CDRInputObject;

import java.util.*;
import java.util.stream.Stream;

public class FileSystem {
    FSDirectory CDrive;
    FSObject getObjectOnPath(String path) {
        String[] dirP = path.substring(4).split("\\\\");
        FSObject obj = this.CDrive;
        int n = dirP.length;
        for(int i=0;i<n;i++){
            String s = dirP[i];
            if(s == null) continue;
            FSDirectory dir = (FSDirectory) obj;
            boolean found = false;
            for(FSObject child: dir.contents){
                if(child.name.equals(s)){
                    found = true;
                    obj = child;
                    break;
                }
            }
            if(!found){
                if(i == n-1){
                    FSFile newFile = new FSFile(s, dir, "");
                    dir.contents.add(newFile);
                }
                else{
                    FSDirectory newDir = new FSDirectory()
                }
            }
        }
        return obj;
    }

    public FSFile createFile(String path, String data){

    }

    public static void main(String[] args) {
    }

    class FSObject {
        protected String name;
        protected FSDirectory parent;

        public FSObject(String name, FSDirectory par) {
            this.name = name;
            this.parent = par;
        }

    }

    class FSDirectory extends FSObject {
        protected ArrayList<FSObject> contents;

        public FSDirectory(String name, FSDirectory par) {
            super(name, par);
            contents = new ArrayList<FSObject>();
        }

    }

    class FSFile extends FSObject {
        private String data;

        //        private int size;
        public FSFile(String name, FSDirectory par, String data) {
            super(name, par);
            this.data = data;
        }

    }
}