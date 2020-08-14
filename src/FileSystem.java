import java.util.ArrayList;

public class FileSystem {
    FSDirectory CDrive;

    FSObject getObjectOnPath(String path) {
        String[] dirP = path.substring(4).split("\\\\");
        FSObject obj = this.CDrive;
        int n = dirP.length;
        for (int i = 0; i < n; i++) {
            String s = dirP[i];
            if (s == null) continue;
            FSDirectory dir = (FSDirectory) obj;
            boolean found = false;
            for (FSObject child : dir.contents) {
                if (child.name.equals(s)) {
                    found = true;
                    obj = child;
                    break;
                }
            }
            if (!found) {
                if (i == n - 1) {
                    FSFile newFile = new FSFile(s, dir, "");
                    dir.contents.add(newFile);
                    obj = newFile;
                } else {
                    FSDirectory newDir = new FSDirectory(s, dir);
                    dir.contents.add(newDir);
                    obj = newDir;
                }
            }
        }
        return obj;
    }

    public int CreateFile(String path, String data) {
        try {
            FSFile file = (FSFile) getObjectOnPath(path);
            file.setData(data);
        } catch (Exception e) {
            return 1;
        }
        return 0;
    }

    public int DeleteFile(String filePath) {
        try {
            FSFile file = (FSFile) getObjectOnPath(filePath);
            file.delete();
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    private int ReplaceFileContent(String path, String data) {
        try {
            FSFile file = (FSFile) getObjectOnPath(path);
            file.setData(data);
            return 0;
        } catch (Exception e) {
            return 1;
        }
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

        int delete() {
            if (this.parent == null) return 1;
            parent.deleteObj(this);
            return 0;
        }
    }

    class FSDirectory extends FSObject {
        protected ArrayList<FSObject> contents;

        public FSDirectory(String name, FSDirectory par) {
            super(name, par);
            contents = new ArrayList<>();
        }

        void deleteObj(FSObject obj) {
            this.contents.remove(obj);
        }

    }

    class FSFile extends FSObject {
        private String data;

        //        private int size;
        public FSFile(String name, FSDirectory par, String data) {
            super(name, par);
            this.data = data;
        }

        void setData(String data) {
            this.data = data;
        }

    }
}