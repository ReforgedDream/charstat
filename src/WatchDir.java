import utils.FileReader;
import utils.FileWriter;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

class WatchDir {
    private final WatchService watcher = FileSystems.getDefault().newWatchService();

    private Path keyPath;
    private Path outputPath;

    /**
     * Creates a WatchService and registers the given directory
     */
    WatchDir(Path dir, Path outputPath) throws IOException, IllegalArgumentException {
        if (dir == null) {
            throw new IllegalArgumentException();
        }
        register(dir);
        if (!outputPath.toFile().exists()) {
            try {
                Files.createDirectory(outputPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.outputPath = outputPath;
    }

    @SuppressWarnings("unchecked")
    private static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>) event;
    }

    /**
     * Register the given directory with the WatchService
     */
    private void register(Path dir) {
        try {
            dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.keyPath = dir;
    }

    /**
     * Process all events for keys queued to the watcher
     */
    void processEvents() {
        for (; ; ) {
            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

            Path dir = this.keyPath;

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                // Context for directory entry event is the file name of entry
                WatchEvent<Path> watchEvent = cast(event);
                Path name = watchEvent.context();
                Path child = dir.resolve(name);

                if (kind == ENTRY_CREATE) {
                    try {
                        String inputString = FileReader.ReadFile(child);
                        String result = TextStatistics.analyzeString(inputString);
                        System.out.println(result);
                        FileWriter.getInstance().writeToFile(child.getFileName().toString(), this.outputPath, result);
                    } catch (FileSystemException e) {
                        System.out.println("File System Exception has occurred");
                        System.out.println(e.getMessage());
                    }
                }
            }

            // reset key and stop processing
            if (!key.reset()) {
                break;
            }
        }
    }
}
