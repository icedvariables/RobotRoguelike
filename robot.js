function update(){
	robot.display(robot.getX() + ", " + robot.getY());
	robot.move(UP);
	var item = robot.dig(UP);
	if(item != null)
		robot.display(item.getName());
}