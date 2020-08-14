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
                    dir.addObject(newFile);
                    obj = newFile;
                } else {
                    FSDirectory newDir = new FSDirectory(s, dir);
                    dir.addObject(newDir);
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

    private int MoveFile(String existingFilePath, String newFilePath) {
        try {
            FSFile orig = (FSFile) getObjectOnPath(existingFilePath);
            FSFile dest = (FSFile) getObjectOnPath(newFilePath);
            dest.setData(orig.readFile());
            orig.delete();
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    private int AddFileLink(String source, String dest) {
        try {
            FSFile sourceFile = (FSFile) getObjectOnPath(source);
            FSFile destFile = (FSFile) getObjectOnPath(dest);
            destFile.data = sourceFile.data;
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

        int addObject(FSObject obj) {
            try {
                this.contents.add(obj);
                return 0;
            } catch (Exception e) {
                return 1;
            }
        }

    }

    class FSFile extends FSObject {
        private Data data;


        //        private int size;
        public FSFile(String name, FSDirectory par, String data) {
            super(name, par);
            this.data = new Data(data);
        }

        void setData(String data) {
            this.data.set(data);
        }

        String readFile() {
            return this.data.data;
        }

    }

    class Data {
        String data;

        public Data(String val) {
            data = val;
        }

        public void set(String val) {
            this.data = val;
        }
    }
}