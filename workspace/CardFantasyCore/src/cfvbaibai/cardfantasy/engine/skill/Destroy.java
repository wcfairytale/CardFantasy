package cfvbaibai.cardfantasy.engine.skill;

import java.util.List;

import cfvbaibai.cardfantasy.GameUI;
import cfvbaibai.cardfantasy.data.Skill;
import cfvbaibai.cardfantasy.engine.CardInfo;
import cfvbaibai.cardfantasy.engine.HeroDieSignal;
import cfvbaibai.cardfantasy.engine.OnAttackBlockingResult;
import cfvbaibai.cardfantasy.engine.OnDamagedResult;
import cfvbaibai.cardfantasy.engine.Player;
import cfvbaibai.cardfantasy.engine.SkillResolver;

public final class Destroy {
    public static void apply(SkillResolver resolver, Skill cardSkill, CardInfo attacker, Player defenderHero,
            int victimCount) throws HeroDieSignal {
        List<CardInfo> victims = resolver.getStage().getRandomizer().pickRandom(
            defenderHero.getField().toList(), victimCount, true, null);
        GameUI ui = resolver.getStage().getUI();
        ui.useSkill(attacker, victims, cardSkill, true);
        for (CardInfo victim : victims) {
            OnAttackBlockingResult result = resolver.resolveAttackBlockingSkills(attacker, victim, cardSkill, 1);
            if (!result.isAttackable()) {
                return;
            }
            ui.killCard(attacker, victim, cardSkill);
            int originalDamage = victim.getHP();
            int actualDamage = victim.applyDamage(victim.getHP());
            resolver.cardDead(victim);
            OnDamagedResult onDamagedResult = new OnDamagedResult();
            onDamagedResult.actualDamage = actualDamage;
            onDamagedResult.originalDamage = originalDamage;
            onDamagedResult.cardDead = true;
            onDamagedResult.unbending = false;
            resolver.resolveDeathSkills(attacker, victim, cardSkill, onDamagedResult);
        }
    }
}
