<html>
<body>
  <h2>Stock Quotes App</h2>
  To test use curl as follows:
  To get a single quote:
  curl http://localhost:8080/stockservice/stockservice/NFLX
  To get a list of quotes:
  curl http://localhost:8080/stockservice/stockservice/NFLX,FB
  To add a stock quote:
  curl -i -X POST -H "Content-Type:application/json" http://localhost:8080/stockservice/stockservice/stocks -d '{"fullName":"Apple Inc","symbol":"AAPL","price":"109.88"}'
  To delete a stock quote:
  curl -v -X DELETE http://localhost:8080/stockservice/stockservice/stocks/AAPL
</body>
</html>
