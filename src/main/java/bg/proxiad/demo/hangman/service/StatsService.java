package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Game;
import bg.proxiad.demo.hangman.model.Stats;
import bg.proxiad.demo.hangman.repository.StatsRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://localhost:8080/", name = "Stats")
public interface StatsService {
  void recordTurn(Stats stats, Character characterPlaced);

  @WebResult(name = "return", targetNamespace = "")
  @RequestWrapper(localName = "getStats",
          targetNamespace = "http://localhost:8080/",
          className = "bg.proxiad.demo.hangman.service.GetStats")
  @WebMethod(action = "urn:GetStats")
  @ResponseWrapper(localName = "getStatsResponse",
          targetNamespace = "http://localhost:8080/",
          className = "bg.proxiad.demo.hangman.service.GetStatsResponse")
  Stats getStats(@WebParam(name = "statsId", targetNamespace = "") Long id);

  Stats decrementLives(Stats stats);
}
