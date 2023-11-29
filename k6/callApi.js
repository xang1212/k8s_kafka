import http from 'k6/http';
import { check } from 'k6';

export let options = {
  vus: 10,
  iterations: 10,
  rps: 10,
  throw: true,
  noConnectionReuse: true,
};

const testData = [
   { adventureSellerId:1, itemId: 1 },
   { adventureSellerId: 2, itemId: 2 },
   { adventureSellerId: 2, itemId: 3 },
   { adventureSellerId: 2, itemId: 4 },
      { adventureSellerId: 2, itemId: 5 },
      { adventureSellerId: 2, itemId: 6 },
      { adventureSellerId: 2, itemId: 7 },
         { adventureSellerId: 2, itemId: 8 },
         { adventureSellerId: 2, itemId: 9 },
         { adventureSellerId: 2, itemId: 10 },
];

export default function () {
  let userIndex = __VU - 1;

  let payload = JSON.stringify(testData[userIndex]);

  let params = {
    headers: {
      'Content-Type': 'application/json',
    },
    timeout: '30s',
  };

  let response = http.post('http://127.0.0.1:51086/adventure/api/insert/marketPlace', payload, params);

  check(response, {
    'status is 201': (r) => r.status === 201,
  });
}
