/*
 * Copyright (c) 2000, 2024, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/* @test
 * @bug 4311940
 * @summary Verify that ObjectOutputStream and ObjectInputStream can be constructed
 * @build AuditStreamSubclass
 * @run main AuditStreamSubclass
 */
import java.io.*;

class GoodOOS1 extends ObjectOutputStream {
    GoodOOS1(OutputStream out) throws IOException { super(out); }
}

class GoodOOS2 extends GoodOOS1 {
    GoodOOS2(OutputStream out) throws IOException { super(out); }
}

class GoodOIS1 extends ObjectInputStream {
    GoodOIS1(InputStream in) throws IOException { super(in); }
}

class GoodOIS2 extends GoodOIS1 {
    GoodOIS2(InputStream in) throws IOException { super(in); }
}

public class AuditStreamSubclass {
    public static void main(String[] args) throws Exception {

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oout = new ObjectOutputStream(bout);
        oout.flush();
        byte[] buf = bout.toByteArray();

        new GoodOOS1(bout);
        new GoodOOS2(bout);
        new GoodOIS1(new ByteArrayInputStream(buf));
        new GoodOIS2(new ByteArrayInputStream(buf));
    }
}
