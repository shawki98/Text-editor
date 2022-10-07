package assign_2;

    //*Node class for main class IV
    // Also includes toString node method for the text to convert to string,
    // also includes the tail node method for the tail node,
    // and includes the length node method to travers through array */
    class Node{
        char item;
        Node next;

        Node(char c, Node l) {
            item = c;
            next = l;
        }

        public String toString(){
            Node p = this;
            String s = "";

            while (p != null){
                s += p.item;
                p = p.next;
            }
            return s;
        }

        public Node tail ( ) {
            Node p = this;
            while(p.next != null)
                p = p.next;
            return p;
        }

        public int length ( ) {
            Node p = this;
            int i = 0;
            while(p != null){
                i++;
                p = p.next;
            }
            return i;
        }
    //*This method is the second command which Will insert T into line L
    //before character index S. If S=0 we insert at front, if S is greater than the current length of line L we
    //insert at the end, otherwise T is inserted before the character indexed by S. This allows insertion of new
    //text anywhere in line L.

        public Node insert ( Node node, int index ) {
            Node p = this, q = p, r = null;
            if(index == 0){
                p = node;
                p.tail().next = q;
                return p;
            }
            else if(index >= length()){
                p.tail().next = node;
                return p;
            }
            else{
                for (int i = 0; i < index; i++) {
                    if (q != null) {
                        r = q;
                        q = q.next;
                    }
                }
                node.tail().next = q;
                r.next = node;
                return p;
            }
        }

    // In this method the first command, The line L is deleted; all lines after L are moved up, The second command
    // for deletion, Deletes a single character from line L, indexed by S.
    // If S indexes a character past the end of the line (S>line length), there is no effect.
    // The third command function for deletion, Removes the characters from index S to E. If
    //S>line length then no effect. If S indexes a valid character and E > line length, then all characters from S
    //to EOL are removed.
        public Node delete ( int start ) {
            Node p = this, r = p, q = null;
            for (int i = 0; i < start; i++) {
                q = r;
                r = r.next;
            }
            if(q != null)
                q.next = r.next;
            else
                p = r.next;
            return p;
        }


        // This method is The third command function for deletion, Removes the characters from index S to E. If
        //S>line length then no effect. If S indexes a valid character and E > line length, then all characters from S
        //to EOL are removed.
        public Node delete ( int start, int end ) {
            if(end > length())
                end = length();
            Node p = this, q = p, r = null;
            for (int i = 0; i < end; i++) {
                if(i < start)
                    r = q;
                q = q.next;
            }
            if(r != null)
                r.next = q;
            else
                p = q;
            return p;
        }

    //this node method is used to replace a character, this hybrid command of delete L S E and insert L S T.
    // Effect is to delete the characters from S to E and insert T before S.
        public Node replace ( Node node, int start, int end ) {
            Node p = this.delete(start, end).insert(node, start);
            return p;
        }

    }
