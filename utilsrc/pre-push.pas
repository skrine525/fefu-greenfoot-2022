program pre;
uses System.IO;

function FileExist(name : string) : boolean;
var f: text;
begin
  assign(f, name);
  try
    reset(f);
    close(f);
    result := true;
  except
    result := false;
  end;
end;

var
  counter: integer;
  finding: boolean;

begin
  counter := 1;
  finding := true;
  
  if not FileExist('.\greenfoot\project.greenfoot') then begin
    writeln('project.greenfoot not found');
    exit;
  end;
  
  while finding do
  begin
    if FileExist('.\greenfoot\project' + counter + '.greenfoot') then
      inc(counter)
    else
      finding := false;
  end;
  &File.Move('.\greenfoot\project.greenfoot', '.\greenfoot\project' + counter + '.greenfoot');
  writeln('project.greenfoot -> project', counter, '.greenfoot');
end.