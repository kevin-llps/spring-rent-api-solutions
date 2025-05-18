-- Insert sample geo_coordinates
INSERT INTO geo_coordinates (id, lon, lat) VALUES
  (UNHEX(REPLACE('11111111-1111-1111-1111-111111111111','-','')), 2.493693307042122, 48.83842553348809),
  (UNHEX(REPLACE('22222222-2222-2222-2222-222222222222','-','')), 2.479562651149885, 48.83649602312711),
  (UNHEX(REPLACE('33333333-3333-3333-3333-333333333333','-','')), 2.470833995083, 48.836022242887);

-- Insert sample velib_station
INSERT INTO velib_station (
    id, stationcode, name, is_installed, capacity, num_docks_available, num_bikes_available,
    mechanical, ebike, is_renting, is_returning, due_date, geo_coordinates_id,
    town_name, code_insee_town, station_opening_hours
) VALUES
  (
    UNHEX(REPLACE('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa','-','')),
    '41303',
    'Gare de Nogent-le-Perreux',
    'OUI',
    0,
    0,
    31,
    8,
    23,
    'OUI',
    'OUI',
    '2025-04-23T12:58:03+00:00',
    UNHEX(REPLACE('11111111-1111-1111-1111-111111111111','-','')),
    'Nogent-sur-Marne',
    '94052',
    NULL
  ),
  (
    UNHEX(REPLACE('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb','-','')),
    '41302',
    'Charles de Gaulle - Beauté',
    'OUI',
    0,
    34,
    15,
    4,
    11,
    'OUI',
    'OUI',
    '2025-04-23T12:59:20+00:00',
    UNHEX(REPLACE('22222222-2222-2222-2222-222222222222','-','')),
    'Nogent-sur-Marne',
    '94052',
    NULL
  ),
  (
    UNHEX(REPLACE('cccccccc-cccc-cccc-cccc-cccccccccccc','-','')),
    '41301',
    'Bois de Vincennes - Gare',
    'OUI',
    0,
    14,
    35,
    27,
    8,
    'OUI',
    'OUI',
    '2025-04-23T12:58:45+00:00',
    UNHEX(REPLACE('33333333-3333-3333-3333-333333333333','-','')),
    'Vincennes',
    '94300',
    NULL
  );
