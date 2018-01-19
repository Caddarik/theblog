/*
 * This file is generated by jOOQ.
*/
package fr.caddarik.theblog.dao.jooq.tables.records;


import fr.caddarik.theblog.dao.jooq.tables.PostTable;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PostRecord extends UpdatableRecordImpl<PostRecord> implements Record5<Integer, Integer, String, String, String> {

    private static final long serialVersionUID = -1785010328;

    /**
     * Setter for <code>post.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>post.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>post.user_id</code>.
     */
    public void setUserId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>post.user_id</code>.
     */
    public Integer getUserId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>post.title</code>.
     */
    public void setTitle(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>post.title</code>.
     */
    public String getTitle() {
        return (String) get(2);
    }

    /**
     * Setter for <code>post.body</code>.
     */
    public void setBody(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>post.body</code>.
     */
    public String getBody() {
        return (String) get(3);
    }

    /**
     * Setter for <code>post.date</code>.
     */
    public void setDate(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>post.date</code>.
     */
    public String getDate() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, String, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, String, String, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return PostTable.POST.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return PostTable.POST.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return PostTable.POST.TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return PostTable.POST.BODY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return PostTable.POST.DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord value2(Integer value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord value3(String value) {
        setTitle(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord value4(String value) {
        setBody(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord value5(String value) {
        setDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostRecord values(Integer value1, Integer value2, String value3, String value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PostRecord
     */
    public PostRecord() {
        super(PostTable.POST);
    }

    /**
     * Create a detached, initialised PostRecord
     */
    public PostRecord(Integer id, Integer userId, String title, String body, String date) {
        super(PostTable.POST);

        set(0, id);
        set(1, userId);
        set(2, title);
        set(3, body);
        set(4, date);
    }
}
