# CoinTask

FUNCTIONALITY

:heavy_check_mark: 1. When	the	app	is	started,	load	and	display	a	list	of	coins
   
   :heavy_check_mark: i. Order	entries	by	name
   
   ii. Filter	the	list	based	on	tags.

:heavy_check_mark: 4. Display	a	divider	between	each	entry

:heavy_check_mark: 5. Display	any	extra	coin	info in	some	kind	of	popup/fragment	when coin entry	is	clicked	based	on	the	second	API	request,	with	said	id as	the	query.

:heavy_check_mark: 6. Provide	some	kind	of	refresh	option	that	reloads	the	list

:heavy_check_mark: 7. Display	an	error	message	if	the	list	cannot	be	loaded	(e.g.	no	network)

:heavy_check_mark: 8. (Extra	credit)	Animate	list	items	(hint	–	Jetpack	Compose	allows	this	easily).

DELIVERABLES

:heavy_check_mark: 1. Write	the	app	using	Kotlin	only.

:heavy_check_mark: 2. Use	Jetpack	Compose	for	UI.

:heavy_check_mark: 3. Use	MVVM	design	pattern,	combined	with	RxJava	or	Coroutines	(remember;	keep	it simple!)

4. Write	unit	tests	against	the	ViewModel	(and	anywhere	you	feel	necessary,	using	i.e.
   Mockito),	so	it’s	tested	that	i.e.	entries	are	ordered	by	name,	etc.

## My Approach
- Worked on making sure the main objectives were completed, however, was unsure of how to display the filter information, whether that would be a button on the screen or a constant display.
- The ViewModelTest is incomplete due to issues with mocking the API service data, the IDE kept suggesting making the function public so it could be used but I opted against that.
- If I had more time, I would work on filtering the names better and making the ViewModelTest work to test entries.
- Sidenote: I kept experiencing API limits due to usage, adding more delays to the implementation.
