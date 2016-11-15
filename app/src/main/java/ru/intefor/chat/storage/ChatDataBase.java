package ru.intefor.chat.storage;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.Sort;
import ru.intefor.chat.entities.Chat;

public class ChatDataBase {
    private Realm realm;

    public ChatDataBase() {
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("chat_db")
                .build();
        realm = Realm.getInstance(configuration);
    }

    public void copyOrUpdate(Chat chat) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(chat);
        realm.commitTransaction();
    }

    public void copyOrUpdate(List<Chat> chats) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(chats);
        realm.commitTransaction();
    }

    public List<Chat> getAllByParticipant(String participant){
        return realm.where(Chat.class).equalTo("participant", participant).findAll();
    }

    public List<Chat> getAll() {
        return realm.where(Chat.class).findAllSorted("updated", Sort.DESCENDING);
    }

    public void close() {
        if (!realm.isClosed()) {
            realm.close();
        }
    }

    public void addChangeListener(RealmChangeListener<Realm> changeListener) {
        realm.addChangeListener(changeListener);
    }
}
