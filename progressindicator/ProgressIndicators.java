package progressindicator;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProgressIndicators {

    public void progressLoad(javafx.scene.control.ProgressIndicator progressIndicator, Task uploadImageWorker, ImageView imageView, String avatarUrl) {
        progressIndicator.setProgress(0);
        progressIndicator.progressProperty().bind(uploadImageWorker.progressProperty());
        new Thread(uploadImageWorker).start();

        uploadImageWorker.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                imageView.setImage(new Image(avatarUrl));
            }
        });
    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 50; i++) {
                    Thread.sleep(50);
                    updateProgress(i + 1, 50);
                }
                return true;

            }
        };
    }
}
