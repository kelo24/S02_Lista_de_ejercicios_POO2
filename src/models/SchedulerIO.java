package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import core.Model;
import core.View;
import java.util.Iterator;

public class SchedulerIO implements Model {

    // Attributes
    private static final String DIRECTORY = ".";
    private static final String FILE = "events.txt";
    private List<View> views = new ArrayList<>();
    private String notice;

    // Methods
    @Override
    public void attach(View view) {
        views.add(view);
    }

    @Override
    public void detach(View view) {
        views.remove(view);
    }

    @Override
    public void notifyViews() {
        for (View v : views) {
            v.update(this, notice);
        }
    }

    /* Saves a {@link SchedulerEvent} in disk in {@link #DIRECTORY}.{@link #FILE} */
    public void saveEvent(SchedulerEvent event) throws Exception {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY, FILE), true));
            writer.write(event.toString(), 0, event.toString().length());
            writer.newLine();
            writer.close();

        } catch (FileNotFoundException fnfe) {
            notice = "File not found";
            notifyViews();
        } catch (Exception ex) {
            notice = "Error while writing the file";
            notifyViews();
        }
    }

    /* Delete event */
    public void deleteEvents(ArrayList<String> listToDelete) throws Exception {

        Vector<Vector<Object>> events = getEvents();
        Vector<Object> toDelete = new Vector<Object>();

        try {
            // Iterar sobre la lista de eventos que se desean eliminar
            for (String nameEvent : listToDelete) {
                // Iterar sobre los eventos en la tabla
                Iterator<Vector<Object>> iterator = events.iterator();
                while (iterator.hasNext()) {
                    Vector<Object> event = iterator.next();

                    // Si el nombre del evento coincide con el nombre que queremos eliminar
                    if (nameEvent.equals(event.get(1))) {  // Suponiendo que el nombre está en la columna 1
                        toDelete.add(event);  // Añadir el evento a la lista de eventos a eliminar
                        iterator.remove();  // Eliminar el evento de la lista original de eventos
                        System.out.println("Evento eliminado: " + event.toString());
                        break;  // Salir del bucle si ya se ha encontrado y eliminado el evento
                    }
                }
            }
            
            // VACIAR ARCHIVO
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY, FILE)));
            writer.close();

            //escribir 1x1
            for (int i = 0; i < events.size(); i++) {

                SchedulerEvent event = new SchedulerEvent();
                event.setDate(SchedulerUtil.getDateFromString(events.get(i).get(0).toString()));
                event.setEventDesc(events.get(i).get(1).toString());
                if (events.get(i).get(2).toString().equals("DAILY")) {
                    event.setFrequency(Frequency.DAILY);
                } else if (events.get(i).get(2).toString().equals("WEEKLY")) {
                    event.setFrequency(Frequency.WEEKLY);
                } else {
                    event.setFrequency(Frequency.MONTHLY);
                }
                event.setFwdEmail(events.get(i).get(3).toString());
                event.setAlarm(Boolean.parseBoolean(events.get(i).get(4).toString()));
                
                // Volver a escribir 1x1
                saveEvent(event);
                
            }

        } catch (Exception ex) {
            notice = "Error while writing the file.d";
            notifyViews();
        }
    }

    /* Reads a {@link SchedulerEvent} saved in disk with name {@link #FILE}. */
    public Vector<Vector<Object>> getEvents() throws Exception {

        Vector<Vector<Object>> response = new Vector<Vector<Object>>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY, FILE)));
            String line = reader.readLine();

            while (line != null) {
                Vector<Object> eventInfo = new Vector<Object>();
                String[] tokens = line.split(";");

                eventInfo.add(tokens[0]);
                eventInfo.add(tokens[1]);
                eventInfo.add(Frequency.valueOf(tokens[2]));
                eventInfo.add(tokens[3]);
                eventInfo.add(tokens[4].equals("1") ? "ON" : "OFF");

                response.add(eventInfo);
                line = reader.readLine();
            }

            reader.close();

        } catch (FileNotFoundException fnfe) {
            notice = "File not found.";
            notifyViews();
        } catch (Exception ex) {
            notice = "There was a problem reading the event file.";
            notifyViews();
        }

        return response;
    }

}
