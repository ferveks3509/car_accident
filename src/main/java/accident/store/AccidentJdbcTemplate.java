package accident.store;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Accident accident, String[] ids, String[] idT) {
        if (accident.getId() == 0) {
            createAccident(accident,ids, idT);
        } else {
            updateAccident(accident, ids, idT);
        }
    }

    private Accident createAccident(Accident accident, String[] ids, String[] idT) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String SQL_INSERT = "insert into accident(name, text, address, type_id) values(?,?,?,?)";
        jdbc.update(connection -> {
           PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
           ps.setString(1, accident.getName());
           ps.setString(2, accident.getText());
           ps.setString(3, accident.getAddress());
           ps.setInt(4, Integer.parseInt(idT[0]));
           return ps;
        }, keyHolder);
        int acId = keyHolder.getKey().intValue();
        createAccidentRule(acId, Integer.parseInt(ids[0]));
        return accident;
    }
    private void createAccidentRule(int idAc, int idRule) {
        jdbc.update("insert into accident_rule(accident_id, rule_id) values (?,?)",
                idAc, idRule);
    }

    private Accident updateAccident(Accident accident, String[] ids, String[] idT) {
       jdbc.update("update accident set name = ?, text = ?, address = ?, type_id = ? where id = ?",
               accident.getName(),
               accident.getText(),
               accident.getAddress(),
               Integer.parseInt(idT[0]),
               accident.getId());
       jdbc.update("update accident_rule set accident_id = ?, rule_id = ? where accident_id = ?",
               accident.getId(),
               Integer.parseInt(ids[0]),
               accident.getId());
        return accident;
    }

    public List<Accident> getAll() {
        return jdbc.query("select * from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));

                    int typeId = rs.getInt("type_id");
                    AccidentType accidentType = findByIdAccidentType(typeId);
                    accident.setAccidentType(accidentType);

                    Set<Rule> rule = new HashSet<>(findByIdAccidentRule(accident.getId()));
                    accident.setRules(rule);
                    return accident;
                });
    }

    public Accident findAccidentById(int id) {
        return jdbc.queryForObject("select * from accident where id = ?",
                new Object[]{id},
                (rs, row) -> {
                    int resultId = rs.getInt("id");
                    Accident accident = new Accident();
                    accident.setId(resultId);
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));

                    int typeId = rs.getInt("type_id");

                    AccidentType type = findByIdAccidentType(typeId);
                    accident.setAccidentType(type);

                    Set<Rule> rules = new HashSet<>(findByIdAccidentRule(resultId));
                    accident.setRules(rules);
                    return accident;
                });
    }

    private AccidentType findByIdAccidentType(int id) {
        return jdbc.queryForObject("select * from type where id = ?",
                new Object[]{id},
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    private List<Rule> findByIdAccidentRule(int id) {
        return jdbc.query("select rule.id, rule.name from accident join accident_rule " +
                        "on accident_rule.accident_id = accident.id join rule " +
                        "on rule.id = accident_rule.rule_id where accident_id = (?)",
                new Object[]{id},
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }
    public List<Rule> getAllRule() {
        return jdbc.query("select * from rule",
                (rs,row) -> {
            Rule rule = new Rule();
            rule.setId(rs.getInt("id"));
            rule.setName(rs.getString("name"));
            return rule;
                });
    }
    public List<AccidentType> getAllAccidentType(){
        return jdbc.query("select * from type",
                (rs,row)-> {
            AccidentType accidentType = new AccidentType();
            accidentType.setId(rs.getInt("id"));
            accidentType.setName(rs.getString("name"));
            return accidentType;
                });
    }
}