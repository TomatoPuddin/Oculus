/*
 * Copyright (c) 2012-2017 The ANTLR Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

package repack.antlr.v4.runtime.atn;

/**  The start of a regular {@code (...)} block. */
public abstract class BlockStartState extends DecisionState {
	public BlockEndState endState;
}
