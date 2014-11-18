/*
 * YourPD
 * Copyright (C) 2014 YourPD team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>
*/

package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import com.dit599.customPD.editorUI.TreeViewAdapter.TreeNode;

public class SuperTreeViewAdapter extends BaseExpandableListAdapter {

	static public class SuperTreeNode {
		Object parent;
		
		List<TreeViewAdapter.TreeNode> childs = new ArrayList<TreeViewAdapter.TreeNode>();
	}

	private List<SuperTreeNode> superTreeNodes = new ArrayList<SuperTreeNode>();
	private Context parentContext;
	private OnChildClickListener stvClickEvent;
	public SuperTreeViewAdapter(Context view,OnChildClickListener stvClickEvent) {
		parentContext = view;
		this.stvClickEvent=stvClickEvent;
	}

	public List<SuperTreeNode> GetTreeNode() {
		return superTreeNodes;
	}

	public void UpdateTreeNode(List<SuperTreeNode> node) {
		superTreeNodes = node;
	}
	
	public void RemoveAll()
	{
		superTreeNodes.clear();
	}
	
	public Object getChild(int groupPosition, int childPosition) {
		return superTreeNodes.get(groupPosition).childs.get(childPosition);
	}

	public int getChildrenCount(int groupPosition) {
		return superTreeNodes.get(groupPosition).childs.size();
	}

	public ExpandableListView getExpandableListView() {
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT, TreeViewAdapter.ItemHeight);
		ExpandableListView superTreeView = new ExpandableListView(parentContext);
		superTreeView.setLayoutParams(lp);
		return superTreeView;
	}

	
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		final ExpandableListView treeView = getExpandableListView();
		final TreeViewAdapter treeViewAdapter = new TreeViewAdapter(this.parentContext,0);
		List<TreeNode> tmp = treeViewAdapter.getTreeNode();
		final TreeNode treeNode=(TreeNode) getChild(groupPosition, childPosition);
		tmp.add(treeNode);
		treeViewAdapter.updateTreeNode((List)tmp);
		treeView.setAdapter(treeViewAdapter);
		
		
		treeView.setOnChildClickListener(this.stvClickEvent);
		
	
		treeView.setOnGroupExpandListener(new OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {
				
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.FILL_PARENT,
						(treeNode.childs.size()+1)*TreeViewAdapter.ItemHeight + 10);
				treeView.setLayoutParams(lp);
			}
		});
		
		
		treeView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {
				
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
						TreeViewAdapter.ItemHeight);
				treeView.setLayoutParams(lp);
			}
		});
		treeView.setPadding(TreeViewAdapter.PaddingLeft*2, 0, 0, 0);
		return treeView;
	}


	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView textView = TreeViewAdapter.getTextView(this.parentContext);
		textView.setText(getGroup(groupPosition).toString());
		textView.setPadding(TreeViewAdapter.PaddingLeft*2, 0, 0, 0);
		return textView;
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public Object getGroup(int groupPosition) {
		return superTreeNodes.get(groupPosition).parent;
	}

	public int getGroupCount() {
		return superTreeNodes.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public boolean hasStableIds() {
		return true;
	}
}
