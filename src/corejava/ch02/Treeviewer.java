package corejava.ch02;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.event.TreeModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CDATASection;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/** 
 * @TODO   解析XML成树形
 * @author yicha
 * @date   2015年8月20日 
 */
public class Treeviewer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame = new DomTreeFrame() ;
		frame.setTitle("DomTreeViewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

class DomTreeFrame extends JFrame{
	private static final int DEFAULT_WIDTH = 400 ;
	private static final int DEFAULT_HEIGHT = 400 ;
	
	private DocumentBuilder builder ;
	
	public DomTreeFrame(){
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JMenu filemenu = new JMenu("File") ;
		JMenuItem openItem = new JMenuItem("Open") ;
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				openFile();
			}
		});
		filemenu.add(openItem) ;
		
		JMenuItem exitItem = new JMenuItem("Exit") ;
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent even) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		filemenu.add(exitItem) ;
		
		JMenuBar bar = new JMenuBar() ;
		bar.add(filemenu) ;
		setJMenuBar(bar);
	}
	
	public void openFile() {
		JFileChooser chooser = new JFileChooser() ;
		chooser.setCurrentDirectory(new File("dom"));
		
		chooser.setFileFilter(new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				// TODO Auto-generated method stub
				return file.isDirectory() || file.getName().toLowerCase().endsWith(".xml");
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "xml file";
			}
		});
		
		int r = chooser.showOpenDialog(this) ;
		if(r != JFileChooser.APPROVE_OPTION) return ;
		
		final File file = chooser.getSelectedFile() ;
		
		new SwingWorker<Document, Void>() {

			@Override
			protected Document doInBackground() throws Exception {
				// TODO Auto-generated method stub
				if(builder == null){
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance() ;
					builder = factory.newDocumentBuilder() ;
				}
				return builder.parse(file);
			}
			
			@Override
			protected void done() {
				// TODO Auto-generated method stub
				try {
					Document doc = get() ;
					JTree tree = new JTree(new DomTreeModel(doc)) ;
					tree.setCellRenderer(new DomTreeCellRenderer());
					
					setContentPane(new JScrollPane(tree));
					validate();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.execute();
	}
}
/**
 * 描述xml文档的树形结构
 * @author Zhangcc
 *
 */
class DomTreeModel implements TreeModel{

	private Document doc ;
	
	public DomTreeModel(Document doc){
		this.doc = doc ;
	}
	
	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getChild(Object parent, int index) {
		// TODO Auto-generated method stub
		Node node = (Node) parent ;
		NodeList list = node.getChildNodes() ;
		return list.item(index);
	}

	@Override
	public int getChildCount(Object parent) {
		// TODO Auto-generated method stub
		Node node = (Node) parent ;
		NodeList list = node.getChildNodes() ;
		return list.getLength();
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		// TODO Auto-generated method stub
		Node node = (Node) parent ;
		NodeList list = node.getChildNodes() ;
		for(int i = 0 ; i < list.getLength() ; i ++){
			if(getChild(parent, i) == child) return i ;
		}
		return -1;
	}

	@Override
	public Object getRoot() {
		// TODO Auto-generated method stub
		return doc.getDocumentElement();
	}

	@Override
	public boolean isLeaf(Object node) {
		// TODO Auto-generated method stub
		return getChildCount(node) == 0;
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub
		
	}
	
}

/**
 * renders an xml node 
 * @author Zhangcc
 *
 */
class DomTreeCellRenderer extends DefaultTreeCellRenderer {
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		// TODO Auto-generated method stub
		Node node = (Node) value ;
		if(node instanceof Element) return elementPanel((Element) node) ;
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,row, hasFocus);
		if(node instanceof CharacterData) setText(characterString((CharacterData) node));
		else setText(node.getClass() + ":" + node.toString());
		
		return this;
	}
	
	public static JPanel elementPanel(Element e){
		JPanel panel = new JPanel() ;
		panel.add(new JLabel("Element:"+ e.getTagName())) ;
		final NamedNodeMap map = e.getAttributes() ;
		panel.add(new JTable(new AbstractTableModel() {
			
			@Override
			public Object getValueAt(int r, int c) {
				// TODO Auto-generated method stub
				return c == 0 ? map.item(r).getNodeName() : map.item(r).getNodeValue() ;
			}
			
			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return map.getLength();
			}
			
			@Override
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return 2;
			}
		}));
		return panel;
	}
	
	public static String characterString(CharacterData node){
		StringBuilder builder = new StringBuilder(node.getData()) ;
		for(int i = 0 ; i < builder.length() ; i ++){
			if(builder.charAt(i) == '\r'){
				builder.replace(i, i+1, "\\r");
				i ++ ;
			}else if(builder.charAt(i) == '\n'){
				builder.replace(i, i+1, "\\n") ;
				i ++ ;
			}else if(builder.charAt(i) == '\t'){
				builder.replace(i, i+1, "\\t") ;
				i ++ ;
			}
		}
		
		if(node instanceof CDATASection) builder.insert(0, "CDATASection:") ;
		else if(node instanceof Text) builder.insert(0, "Text:");
		else if(node instanceof Comment) builder.insert(0, "Comment:") ;
		
		return builder.toString() ;
	}
}