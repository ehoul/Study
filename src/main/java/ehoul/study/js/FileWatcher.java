package ehoul.study.js;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.Iterator;

import com.tbt.smartgarments.nestingEngine.svg.LuanchChrom;

import static java.nio.file.StandardWatchEventKinds.*;

import java.awt.AWTException;


public class FileWatcher {
	private WatchService watcher;

    private Path path;
    
    public static LuanchChrom luanchChrom = new LuanchChrom();
    public FileWatcher(Path path) throws IOException {
        this.path = path;
        watcher = FileSystems.getDefault().newWatchService();
        this.path.register(watcher, OVERFLOW, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
    }

    public void handleEvents(String filePath) throws InterruptedException {
    	//init luanchChrom
    	luanchChrom.setClosetime(2000);
    	luanchChrom.setLayouttime(10000);
    	luanchChrom.setDownloadPath("C:\\PData\\NestingHints\\");
    	luanchChrom.setUploadpath(filePath);
    	luanchChrom.setWebdriver("webdriver.chrome.driver");
    	luanchChrom.setWebdriverpath("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        luanchChrom.setWeburl("http://192.168.1.117:8080/SVGnest-master/index.html");
    	// start to process the data files
        while (true) {
            // start to handle the file change event
            final WatchKey key = watcher.take();

            for (WatchEvent<?> event : key.pollEvents()) {
                // get event type
                final WatchEvent.Kind<?> kind = event.kind();

                // get file name
                @SuppressWarnings("unchecked")
                final WatchEvent<Path> pathWatchEvent = (WatchEvent<Path>) event;
                final Path fileName = pathWatchEvent.context();
                if (kind == ENTRY_CREATE) {
                    System.out.println("create:"+fileName.getFileName());
                    // 说明点1
                    // 监测创建的文件是否是SVG文件
                    if (fileName.toString().endsWith(".svg")) {
                    	String svgHintName =  fileName.toString().replaceAll(".svg", ".hint");
                    	LuanchChrom luanchChrom = new LuanchChrom();
                        try {
							luanchChrom.genHintBySVG(fileName.toString());
						} catch (AWTException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
                } else if (kind == ENTRY_DELETE) {
                	System.out.println("delete");
                    // 说明点1
                    // create a new thread to monitor the new file
                   
                    // todo
                } else if (kind == ENTRY_MODIFY) {
                	System.out.println("modefy");
                    // todo
                } else if (kind == OVERFLOW) {
                	System.out.println("overflow");
                    // todo
                }
            }

            // IMPORTANT: the key must be reset after processed
            if (!key.reset()) {
                return;
            }
        }
    }

    public static void main(String args[]) throws IOException, InterruptedException {
        String filePath = "C:\\PData\\pieceSVGImages\\";
    	new FileWatcher(Paths.get(filePath, "")).handleEvents(filePath);
    }
}
