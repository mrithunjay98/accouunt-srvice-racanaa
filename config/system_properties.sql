INSERT INTO `system_config` (`name`, `tenant_id`, `date_created`, `date_updated`, `value`)
VALUES
	('service.name', '402881888abbf1f3018abbf1f6a00007', now(), now(), 'account'),
	('beans.inspect', '402881888abbf1f3018abbf1f6a00007', now(), now(), '0'),
	('api.key.header.name', '402881888abbf1f3018abbf1f6a00007', now(), now(), 'x-api-key'),
    ('api.key.value', '402881888abbf1f3018abbf1f6a00007', now(), now(), '6e399983-862e-4435-808a-4056ebc4f841'),
    ('jwt.token.header.name', '402881888abbf1f3018abbf1f6a00007', now(), now(), 'Authorization'),
    ('jwt.token.secret', '402881888abbf1f3018abbf1f6a00007', now(), now(), 'A7D2A23FAFF1B9DEA2BE9D6BEAC51'),
    ('jwt.token.expiry.seconds', '402881888abbf1f3018abbf1f6a00007', now(), now(), '3600')
    ;