/*
 * Copyright (c) 2012-2017 The ANTLR Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

package repack.antlr.v4.runtime.misc;

import java.lang.reflect.Method;

/** A proxy for the real repack.antlr.v4.gui.TestRig that we moved to tool
 *  artifact from runtime.
 *
 *  @deprecated
 *  @since 4.5.1
 */
@Deprecated
public class TestRig {
	public static void main(String[] args) {
		try {
			Class<?> testRigClass = Class.forName("repack.antlr.v4.gui.TestRig");
			System.err.println("Warning: TestRig moved to repack.antlr.v4.gui.TestRig; calling automatically");
			try {
				Method mainMethod = testRigClass.getMethod("main", String[].class);
				mainMethod.invoke(null, (Object)args);
			}
			catch (Exception nsme) {
				System.err.println("Problems calling repack.antlr.v4.gui.TestRig.main(args)");
			}
		}
		catch (ClassNotFoundException cnfe) {
			System.err.println("Use of TestRig now requires the use of the tool jar, antlr-4.X-complete.jar");
			System.err.println("Maven users need group ID repack.antlr and artifact ID antlr4");
		}
	}
}
