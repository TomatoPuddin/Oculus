/*
 * Copyright (c) 2012-2017 The ANTLR Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

package repack.antlr.v4.runtime.tree.xpath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import repack.antlr.v4.runtime.tree.ParseTree;
import repack.antlr.v4.runtime.tree.TerminalNode;
import repack.antlr.v4.runtime.tree.Tree;
import repack.antlr.v4.runtime.tree.Trees;

public class XPathTokenElement extends XPathElement {
	protected int tokenType;
	public XPathTokenElement(String tokenName, int tokenType) {
		super(tokenName);
		this.tokenType = tokenType;
	}

	@Override
	public Collection<ParseTree> evaluate(ParseTree t) {
		// return all children of t that match nodeName
		List<ParseTree> nodes = new ArrayList<ParseTree>();
		for (Tree c : Trees.getChildren(t)) {
			if ( c instanceof TerminalNode ) {
				TerminalNode tnode = (TerminalNode)c;
				if ( (tnode.getSymbol().getType() == tokenType && !invert) ||
					 (tnode.getSymbol().getType() != tokenType && invert) )
				{
					nodes.add(tnode);
				}
			}
		}
		return nodes;
	}
}
