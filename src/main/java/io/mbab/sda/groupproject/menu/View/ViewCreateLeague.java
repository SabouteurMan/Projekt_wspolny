package io.mbab.sda.groupproject.menu.View;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuAction;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.menu.action.LeagueAction;
import io.mbab.sda.groupproject.service.CountryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ViewCreateLeague implements MenuAction {

  private final CustomScanner cs;
  private final LeagueAction leagueAction;
  private final MenuActionContext ctx;
  private final CountryService countryService;

  @Override
  public void execute() {

    System.out.println("!!! DODAJESZ LIGĘ !!!");
    System.out.println("--> Wciśnięcie '0' powoduję powtór do menu głównego <--");
    System.out.println("Jaka nazwa ligi?");

    String leagueName = cs.nextLine();
    leagueAction.pressedZero(leagueName);
    createLeague(leagueName);

    ctx.execute();
  }

  public League createLeague(String leagueName) {

    System.out.println("!!! DODAJESZ LIGĘ !!!");
    System.out.println("Z jakiego Państwa jest ta liga?");

    String countryName = cs.nextLine();
    leagueAction.pressedZero(countryName);

    Country country = leagueAction.getCountry(countryName);
    countryService.save(country);

    League league = leagueAction.getLeague(leagueName, country);

    leagueAction.save(league);

    System.out.println(
        "Dodano ligę o nazwie: " + leagueName + ", kraj pochodzenia: " + countryName);
    return league;
  }
}
