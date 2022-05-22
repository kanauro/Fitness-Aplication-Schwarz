package sk.uniba.fmfi.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import sk.uniba.fmfi.model.Database;
import sk.uniba.fmfi.model.BodyRecord;
import sk.uniba.fmfi.view.ExceptionDialog;

import java.time.LocalDate;

@AllArgsConstructor
public class CreateRecordEventHandler implements EventHandler {

    private final Stage stage;
    private final Database database;
    private final TextField weightField;
    private final TextField heightField;
    private final TextField armField;
    private final TextField neckField;
    private final TextField waistField;
    private final TextField hipField;

    @SneakyThrows
    @Override
    public void handle(Event event) {

        BodyValuesCalculator bodyValuesCalculator = new BodyValuesCalculator(database.getUserInfo());

        try {
            database.getUserInfo().setLatestHeight(Float.parseFloat(heightField.getText()));
            database.getUserInfo().setLatestWeight(Float.parseFloat(weightField.getText()));

            BodyRecord bodyRecord = BodyRecord.builder()
                    .id(database.getRecordsList().size() + 1)
                    .date(LocalDate.now().toString())
                    .weight(Float.parseFloat(weightField.getText()))
                    .height(Float.parseFloat(heightField.getText()))
                    .arm(Float.parseFloat(armField.getText()))
                    .hip(Float.parseFloat(hipField.getText()))
                    .waist(Float.parseFloat(waistField.getText()))
                    .neck(Float.parseFloat(neckField.getText()))
                    .build();

            bodyValuesCalculator.setBodyValues(bodyRecord);

            if (!database.saveRecord(bodyRecord).equals("Success")) {
                throw new IllegalStateException("Záznam sa nepodarilo úložiť do súboru.");
            }

            stage.close();
        } catch (NumberFormatException e) {
            new ExceptionDialog(new IllegalStateException("Nesprávne zadané údaje. Všetky polia musia býť vyplnené."));
        } catch (IllegalArgumentException e) {
            new ExceptionDialog(new IllegalStateException(e.getMessage()));
        } catch (IllegalStateException e) {
            new ExceptionDialog(e);
        }
    }
}
